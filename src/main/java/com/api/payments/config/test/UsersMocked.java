package com.api.payments.config.test;

import com.api.payments.dto.UserConfigurationsDto;
import com.api.payments.dto.UsersDto;
import com.api.payments.entity.UserConfigurations;
import com.api.payments.entity.Users;
import com.api.payments.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Configuration
@AllArgsConstructor
public class UsersMocked {

    private static final UUID ID = UUID.fromString("4f9ab8ae-e62a-40f9-b7b8-66eb1d30b75a");
    private static final String USER_NAME = "Johann";
    private static final String EMAIL = "johann@gmail.com.br";
    private static final String PASSW = "Johann1234#@";
    private static final String PHONE = "5551999999999";
    private static final boolean HAS_NOTIF = true;
    private static final String LANGUAGE = "pt_BR";

    private UserRepository userRepository;

    @Bean
    public UsersDto returnUserDtoMocked(){
        val userConfigurations =
                UserConfigurationsDto.builder()
                        .hasNotifications(HAS_NOTIF)
                        .language(LANGUAGE)
                        .build();

        return UsersDto.builder()
                .id(ID)
                .name(USER_NAME)
                .email(EMAIL)
                .password(PASSW)
                .phone(PHONE)
                .userConfigurations(userConfigurations)
                .build();
    }

    @Bean
    public Users returnUserMocked(){
        val userConfigurations = new UserConfigurations();
        userConfigurations.setHasNotifications(HAS_NOTIF);
        userConfigurations.setLanguage(LANGUAGE);

        val user = new Users();
        user.setName(USER_NAME);
        user.setEmail(EMAIL);
        user.setPassword(PASSW);
        user.setPhone(PHONE);
        user.setUserConfigurations(userConfigurations);

        return user;
    }

    @Bean
    public Optional<Users> returnOptionalUserMocked() {
        return Optional.of(returnUserMocked());
    }

    public void usersDB() {
        val userMocked = returnUserMocked();
        Users u1 = new Users(userMocked.name, userMocked.email, userMocked.password, userMocked.phone, userMocked.userConfigurations);
        Users u2 = new Users(userMocked.name, userMocked.email, userMocked.password, userMocked.phone, userMocked.userConfigurations);
        userRepository.saveAll(List.of(u1, u2));
    }
}
