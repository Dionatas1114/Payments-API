package com.api.payments.services.impl;

import com.api.payments.dto.DigitCodeDto;
import com.api.payments.dto.LoginDto;
import com.api.payments.dto.TokenDto;
import com.api.payments.entity.Users;
import com.api.payments.interceptor.TokenInterceptor;
import com.api.payments.repository.UserRepository;
import com.api.payments.services.UserAccessService;
import com.api.payments.utils.Log;
import com.api.payments.validations.Patterns;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import javax.naming.AuthenticationException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;

import static com.api.payments.messages.UserAccessMessages.*;

@Service
@AllArgsConstructor
public class UserAccessServiceImpl implements UserAccessService {

    private UserRepository userRepository;
    private TokenInterceptor tokenInterceptor;

    @Override
    public TokenDto validateUserCredentials(LoginDto loginDto) throws Exception {

        Log.warn("Starting Login...");

        String email = loginDto.getEmail();
        String rawPassword = loginDto.getPassword();

        String encodedPassword = validateUserEmail(email);
        validateUserPassword(rawPassword, encodedPassword);

        Log.info("[" + email + "] successful email Login ✔");
        return tokenInterceptor.generateToken(email);
    }

    private String validateUserEmail(String email) throws Exception {

        boolean emailIsValid = Patterns.emailValidate(email);

        if (!emailIsValid) throw new AuthenticationException(invalidEmail);

        Users userByEmail = userRepository.findByEmail(email).orElseThrow(() -> new NotFoundException(userNotFound));

        LoginDto userAccessData = convertToDto(userByEmail);

        return userAccessData.getPassword();
    }

    private void validateUserPassword(String rawPassword, String encodedPassword) throws Exception {

        boolean passwordIsValid = tokenInterceptor.passwordEncoder().matches(rawPassword, encodedPassword);

        if (!passwordIsValid) throw new AuthenticationException(invalidPassword);
    }

    @Override
    public TokenDto getDigitCode(DigitCodeDto digitCodeDto) throws Exception {

        String token = digitCodeDto.token;
//        tokenInterceptor.validateToken(token, response);
        String usernameFromToken = tokenInterceptor.getUsernameFromToken(token);
        Integer length = digitCodeDto.length;
        String code = generateDigitCode(length);

        TokenDto tokenDto = new TokenDto();
        tokenDto.setToken(code);
        tokenDto.setExpiresIn(usernameFromToken);

        return tokenDto;
    }

    private String generateDigitCode(Integer length) throws Exception {
        Log.warn("Generating [" + length + " digit code] ...");

        if (length <= 0) throw new Exception("Length must be a positive integer");

        int min = (int) Math.pow(10, length - 1);
        int max = (int) Math.pow(10, length) - 1;

        Log.warn("max: " + max + ", min: " + min);

        SecureRandom random = new SecureRandom();
        return String.valueOf(random.nextInt((max - min) + 1) + min);
    }

    private LoginDto convertToDto(Users user) {
        return new ModelMapper().map(user, LoginDto.class);
    }
}
