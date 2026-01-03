package com.api.payments.controller;

import com.api.payments.mocks.UsersMocked;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    UsersMocked user;

    @Test
    @DisplayName("Should return all users")
    void findAllUsers() {
        assertEquals(2, 1 + 1);
    }

    @Test
    void findUserById() {
    }

    @Test
    void createUser() {

//        var userData = user.returnUserDataMocked();
    }

    @Test
    void updateUser() {
    }

    @Test
    void deleteUser() {
    }
}
