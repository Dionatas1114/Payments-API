package com.api.payments.services.impl;

import com.api.payments.entity.UserConfigurations;
import com.api.payments.entity.Users;
import com.api.payments.repository.UserRepository;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace=AutoConfigureTestDatabase.Replace.NONE)
public class UserServiceImplTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void findAllUsers() {

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

        var user1 = testEntityManager.persist(users);

        var usersListFromRepository = userRepository.findAll();

        Assertions.assertEquals(1, usersListFromRepository.size());
    }

    @Test
    public void findUserById() {
    }

    @Test
    public void saveUserData() {
    }

    @Test
    public void updateUserData() {
    }

    @Test
    public void deleteUserData() {
    }
}
