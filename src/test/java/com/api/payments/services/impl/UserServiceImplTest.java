package com.api.payments.services.impl;

import com.api.payments.dto.UsersDto;
import com.api.payments.entity.UserConfigurations;
import com.api.payments.entity.Users;
import com.api.payments.repository.UserRepository;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.sonatype.aether.RepositoryException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
public class UserServiceImplTest {

    @TestConfiguration
    public static class UserServiceImplTestConfig{

//        @Bean
//        public UserServiceImpl userService (){
//            return new UserServiceImpl();
//        }
    }

    @Autowired
    UserServiceImpl userService;

    @MockBean
    UserRepository userRepository;

    @Test
    public void findAllUsers() throws RepositoryException {

        UUID id = UUID.fromString("4f9ab8ae-e62a-40f9-b7b8-66eb1d30b75a");
        UsersDto user = userService.findUserById(id);

        UsersDto userFound = new UsersDto();

        String name = "teste";
        String email = "teste@gmail.com";
        String password = "teste123#";

        userFound.setName(name);
        userFound.setEmail(email);
        userFound.setPassword(password);

        assertEquals(user, userFound);
    }

    @Before
    public void setup(){

        UUID id = UUID.fromString("4f9ab8ae-e62a-40f9-b7b8-66eb1d30b75a");
        String name = "teste";
        String email = "teste@gmail.com";
        String password = "teste123#";
        String phone = "teste123#";
        boolean hasNotification = true;
        String language = "pt_BR";

        UserConfigurations userConfigurations = new UserConfigurations();
        userConfigurations.setHasNotifications(hasNotification);
        userConfigurations.setLanguage(language);

        Users users = new Users();
        users.setName(name);
        users.setEmail(email);
        users.setPassword(password);
        users.setPhone(phone);
        users.setUserConfigurations(userConfigurations);

        Mockito.when(userRepository.findById(id)).thenReturn(Optional.of(users));
    }

    @Test
    void findUserById() {
    }

    @Test
    void saveUserData() {
    }

    @Test
    void updateUserData() {
    }

    @Test
    void deleteUserData() {
    }
}
