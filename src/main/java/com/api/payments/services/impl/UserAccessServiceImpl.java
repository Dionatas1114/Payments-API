package com.api.payments.services.impl;

import com.api.payments.config.SecurityConfig;
import com.api.payments.dto.LoginDto;
import com.api.payments.dto.TokenDto;
import com.api.payments.entity.Users;
import com.api.payments.repository.UserRepository;
import com.api.payments.services.UserAccessService;
import lombok.AllArgsConstructor;
import lombok.val;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserAccessServiceImpl implements UserAccessService {

    private UserRepository userRepository;

    private SecurityConfig securityConfig;

    @Override
    public TokenDto findUserByEmail(LoginDto loginDto) throws Exception {

        String email = loginDto.getEmail();
        val userByEmail = userRepository.findByEmail(email);
        if(userByEmail == null) throw new Exception("Usuário não encontrado");

        val userAccessData = convertToDto(userByEmail);

        String rawPassword = loginDto.getPassword();
        String encodedPassword = userAccessData.getPassword();

        boolean passwordIsValid = securityConfig.passwordEncoder().matches(rawPassword, encodedPassword);

        if(!passwordIsValid) throw new Exception("Senha Incorreta");

        return securityConfig.generateToken(email);
    }

    private LoginDto convertToDto(Users user) {
        return new ModelMapper().map(user, LoginDto.class);
    }
}
