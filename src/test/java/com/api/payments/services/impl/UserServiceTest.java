package com.api.payments.services.impl;

import com.api.payments.dto.UsersDto;
import com.api.payments.entity.Users;
import com.api.payments.mocks.UserTest;
import com.api.payments.repository.UserRepository;
import com.api.payments.services.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;
import java.util.UUID;

@RunWith(SpringRunner.class)
@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @InjectMocks
    UserService userService;

//    @Mock
    UserTest userTest;

    @Mock
    UserRepository userRepository;

    @Test
    public void saveUserData() throws Exception {

        UsersDto userData = userTest.returnUserDtoData();

//        Optional<Users> user = userRepository.findById(userId);

        UUID id = UUID.fromString("4f9ab8ae-e62a-40f9-b7b8-66eb1d30b75a");
        UsersDto usersDto = userService.findUserById(id);

        Assertions.assertEquals(userData, usersDto);

    }

    @Before
    public void setup(){

        UUID userId = userTest.returnUserDtoData().id;
        Users user = userTest.returnUserData();

        Mockito.when(userRepository.findById(userId))
                .thenReturn(Optional.ofNullable(user));
    }
}
