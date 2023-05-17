package com.api.payments.config.test;

import com.api.payments.entity.Services;
import com.api.payments.repository.ServiceRepository;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Configuration
@AllArgsConstructor
public class ServicesMocked {

    private static final UUID ID = UUID.fromString("4f9ab8ae-e62a-40f9-b7b8-66eb1d30b75a");
    private static final String USER_NAME = "Johann";
    private static final String EMAIL = "johann@gmail.com.br";
    private static final String PASSW = "Johann1234#@";
    private static final String PHONE = "5551999999999";
    private static final boolean HAS_NOTIF = true;
    private static final String LANGUAGE = "pt_BR";

    private ServiceRepository serviceRepository;

//    @Bean
//    public UsersDto returnUserDtoMocked(){
//        var userConfigurations =
//                UserConfigurationsDto.builder()
//                        .hasNotifications(HAS_NOTIF)
//                        .language(LANGUAGE)
//                        .build();
//
//        return UsersDto.builder()
//                .id(ID)
//                .name(USER_NAME)
//                .email(EMAIL)
//                .password(PASSW)
//                .phone(PHONE)
//                .userConfigurations(userConfigurations)
//                .build();
//    }

    @Bean
    public Services returnServiceMocked(){

        val service = new Services();
//        user.setName(USER_NAME);
//        user.setEmail(EMAIL);
//        user.setPassword(PASSW);
//        user.setPhone(PHONE);
//        user.setUserConfigurations(userConfigurations);

        return service;
    }

    @Bean
    public Optional<Services> returnOptionalUserMocked() {
        return Optional.of(returnServiceMocked());
    }

    public void servicesDB() {
        val s1 = new Services();
        val s2 = new Services();
        serviceRepository.saveAll(List.of(s1, s2));
    }
}
