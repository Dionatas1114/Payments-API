package com.api.payments.mocks;

import com.api.payments.dto.UserConfigurationsDto;
import com.api.payments.dto.UsersDto;

public class User {

    private static final String USER_NAME = "Johann";
    private static final String EMAIL = "johann@gmail.com.br";
    private static final String PASSW = "Johann1234#@";
    private static final String PHONE = "5551999999999";
    private static final boolean HAS_NOTIF = true;
    private static final String LANGUAGE = "pt_BR";

    public UsersDto returnUserData(){

        var userConfigurations =
                UserConfigurationsDto.builder()
                        .hasNotifications(HAS_NOTIF)
                        .language(LANGUAGE)
                        .build();

        return UsersDto.builder()
                .name(USER_NAME)
                .email(EMAIL)
                .password(PASSW)
                .phone(PHONE)
                .userConfigurations(userConfigurations)
                .build();
    }
}
