package com.api.payments.controller;

import com.api.payments.mocks.UserTest;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@AllArgsConstructor
class UserControllerTest {

    UserTest user;

    @Test
    @DisplayName(" should")
    void findAllUsers() {
        assertEquals(2, 1 + 1);
    }

    @Test
    void findUserById() {
    }

    @Test
    void createUser() {

        var userData = user.returnUserData();
    }

    @Test
    void updateUser() {
    }

    @Test
    void deleteUser() {
    }
}