package com.api.payments.mocks;

import com.api.payments.dto.UserConfigurationsDto;
import com.api.payments.dto.UsersDto;
import com.api.payments.entity.UserConfigurations;
import com.api.payments.entity.Users;

import java.util.Optional;
import java.util.UUID;

public class UsersMocked {

    private static final UUID ID = UUID.fromString("4f9ab8ae-e62a-40f9-b7b8-66eb1d30b75a");
    private static final String USER_NAME = "Johann";
    private static final String EMAIL = "johann@gmail.com.br";
    private static final String PASSW = "Johann1234#@";
    private static final String PHONE = "5551999999999";
    private static final boolean HAS_NOTIF = true;
    private static final String LANGUAGE = "pt_BR";

    public UsersDto returnUserDtoMocked(){
        var userConfigurations =
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

    public Users returnUserMocked(){
        var userConfigurations = new UserConfigurations();
        userConfigurations.setHasNotifications(HAS_NOTIF);
        userConfigurations.setLanguage(LANGUAGE);

        var user = new Users();
        user.setName(USER_NAME);
        user.setEmail(EMAIL);
        user.setPassword(PASSW);
        user.setPhone(PHONE);
        user.setUserConfigurations(userConfigurations);

        return user;
    }

    public Optional<Users> returnOptionalUserMocked() {
        Users user = returnUserMocked();
        return Optional.of(user);
    }
}
