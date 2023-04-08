package com.api.payments.services.impl;

import com.api.payments.config.SecurityConfig;
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

import static com.api.payments.messages.UserAccessMessages.*;

@Service
@AllArgsConstructor
public class UserAccessServiceImpl implements UserAccessService {

    private UserRepository userRepository;
    private SecurityConfig securityConfig;

    @Override
    public TokenDto findUserByEmail(LoginDto loginDto) throws ExceptionInInitializerError {

        String email = loginDto.getEmail();
        Log.warn("Iniciando Login...");

        val userByEmail = userRepository.findByEmail(email);
        if(userByEmail == null) {
            Log.error("Erro no Login: " + invalidEmail + "[ " + email + " ]. ");
            throw new ExceptionInInitializerError(invalidEmail);
        }

        val userAccessData = convertToDto(userByEmail);

        String rawPassword = loginDto.getPassword();
        String encodedPassword = userAccessData.getPassword();

        boolean passwordIsValid = securityConfig.passwordEncoder().matches(rawPassword, encodedPassword);

        if(!passwordIsValid) {
            Log.error("Erro no Login: " + invalidPassword);
            throw new ExceptionInInitializerError(invalidPassword);
        }

        Log.info("Login para email: " + email + " ✔");
        return securityConfig.generateToken(email);
    }

    private LoginDto convertToDto(Users user) {
        return new ModelMapper().map(user, LoginDto.class);
    }
}
