package com.api.payments.services.impl;

import com.api.payments.dto.UsersDto;
import com.api.payments.entity.Users;
import com.api.payments.mocks.UserTest;
import com.api.payments.repository.UserRepository;
import com.api.payments.services.UserService;
import lombok.AllArgsConstructor;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;
import java.util.UUID;

@RunWith(SpringRunner.class)
@AllArgsConstructor
public class UserServiceTest {

    UserTest userTest;
    UserService userService;
    UserRepository userRepository;

    @Test
    public void saveUserData() throws Exception {

        UsersDto userData = userTest.returnUserDtoData();

        UUID id = UUID.fromString("4f9ab8ae-e62a-40f9-b7b8-66eb1d30b75a");
        UsersDto user = userService.findUserById(id);

        Assertions.assertEquals(userData, user);

    }

    @Before
    public void setup(){

        UUID userId = userTest.returnUserDtoData().id;
        Users user = userTest.returnUserData();

        Mockito.when(userRepository.findById(userId))
                .thenReturn(Optional.ofNullable(user));
    }
}
