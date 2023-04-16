package com.api.payments.services.impl;

import com.api.payments.PaymentsApplication;
import com.api.payments.PaymentsApplicationTests;
import com.api.payments.entity.UserConfigurations;
import com.api.payments.entity.Users;
import com.api.payments.mocks.UsersMocked;
import com.api.payments.repository.UserRepository;
import com.api.payments.utils.Log;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace=AutoConfigureTestDatabase.Replace.NONE)
public class UserServiceImplTest {

//    @Autowired
//    private TestEntityManager testEntityManager;

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private UserServiceImpl userService;

    @Test
    @DisplayName("This test should return all user data")
    public void findAllUsers() {

//        UUID id = UUID.fromString("4f9ab8ae-e62a-40f9-b7b8-66eb1d30b75a");
//        String name = "teste";
//        String email = "teste@gmail.com";
//        String password = "teste123#";
//        String phone = "teste123#";
//        boolean hasNotification = true;
//        String language = "pt_BR";
//
//        UserConfigurations userConfigurations = new UserConfigurations();
//        userConfigurations.setHasNotifications(hasNotification);
//        userConfigurations.setLanguage(language);
//
//        Users users = new Users();
//        users.setName(name);
//        users.setEmail(email);
//        users.setPassword(password);
//        users.setPhone(phone);
//        users.setUserConfigurations(userConfigurations);
//
//        var usersListFromRepository = userRepository.findAll();
//
//        Assertions.assertEquals(1, usersListFromRepository.size());
        List<Users> userList = new ArrayList<>();
        var userDataMocked = new UsersMocked().returnUserDataMocked();
        userList.add(userDataMocked);
//        var userPersisted = testEntityManager.persist(userDataMocked);
//        Log.info("## userPersisted, userName: " + userPersisted.getName());

        Mockito.when(userRepository.findAll()).thenReturn(userList);

//        var allUsers = userService.findAllUsers();
//
//        Assertions.assertEquals(1, allUsers.size());
//        Assertions.assertEquals(userDataMocked.getName(), allUsers.get(0).getName());
    }

    @Test
    public void findUserById() {
        var userDtoDataMocked = new UsersMocked().returnUserDtoDataMocked();
        var userDataMocked = new UsersMocked().returnUserDataMocked();

        UUID userId = userDtoDataMocked.getId();
        Mockito.when(userRepository.findById(ArgumentMatchers.eq(userId))).thenReturn(Optional.ofNullable(userDataMocked));

        var user = userService.findUserById(userId);

        Mockito.verify(userRepository, Mockito.times(1));
//        Assertions.assertEquals(userDataMocked.getName(), user.getName());
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
