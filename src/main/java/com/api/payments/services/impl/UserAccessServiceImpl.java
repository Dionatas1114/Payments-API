package com.api.payments.services.impl;

import com.api.payments.config.SecurityConfig;
import com.api.payments.dto.DigitCodeDto;
import com.api.payments.dto.LoginDto;
import com.api.payments.dto.TokenDto;
import com.api.payments.entity.Users;
import com.api.payments.repository.UserRepository;
import com.api.payments.services.UserAccessService;
import com.api.payments.utils.Log;
import lombok.AllArgsConstructor;
import lombok.val;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Random;

import static com.api.payments.messages.UserAccessMessages.*;

@Service
@AllArgsConstructor
public class UserAccessServiceImpl implements UserAccessService {

    private UserRepository userRepository;
    private SecurityConfig securityConfig;

    @Override
    public TokenDto findUserByEmail(LoginDto loginDto) throws ExceptionInInitializerError {

        Log.warn("Starting Login...");
        String email = loginDto.getEmail();
        // TODO validar email - regex
        if(email == null) {
            Log.error("Login Error: " + invalidEmail + "[ " + email + " ].");
            throw new ExceptionInInitializerError(invalidEmail);
        }

        val userByEmail = userRepository.findByEmail(email);
        if(userByEmail == null) {
            Log.error("Login Error: " + userNotFound + "[ " + email + " ].");
            throw new ExceptionInInitializerError(userNotFound);
        }

        val userAccessData = convertToDto(userByEmail);

        String rawPassword = loginDto.getPassword();
        String encodedPassword = userAccessData.getPassword();

        boolean passwordIsValid = securityConfig.passwordEncoder().matches(rawPassword, encodedPassword);

        if(!passwordIsValid) {
            Log.error("Login error: " + invalidPassword);
            throw new ExceptionInInitializerError(invalidPassword);
        }

        Log.info("[" + email + "] successful email Login ✔");
        return securityConfig.generateToken(email);
    }

    @Override
    public TokenDto getDigitCode(DigitCodeDto digitCodeDto) throws Exception {

        String token = digitCodeDto.token;
        securityConfig.validateToken(token);
        String usernameFromToken = securityConfig.getUsernameFromToken(token);
        Integer length = digitCodeDto.length;
        String code = generateDigitCode(length);

        TokenDto tokenDto = new TokenDto();
        tokenDto.setToken(code);
        tokenDto.setExpiresIn(usernameFromToken);

        return tokenDto;
    }

    private String generateDigitCode(Integer length) {
        Log.warn("Generating [" + length + " digit code] ...");
        if (length == null || length == 0) throw new ExceptionInInitializerError("");

        StringBuilder builder = new StringBuilder();
        String maxValue = "";
        int i = 0;

        while (i < length) {
            maxValue = String.valueOf(builder.append("9"));
            i++;
        }

        int max = Integer.parseInt(maxValue);
        int min = Integer.parseInt(maxValue.substring(0, maxValue.length() - 1));
        Log.warn("max: " + max + ", min: " + min);

        return String.valueOf(new Random().nextInt(max + 1 - min) + min);
    }

    private LoginDto convertToDto(Users user) {
        return new ModelMapper().map(user, LoginDto.class);
    }
}
