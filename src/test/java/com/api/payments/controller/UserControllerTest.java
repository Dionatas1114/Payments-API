package com.api.payments.controller;

import com.api.payments.mocks.UsersMocked;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@AllArgsConstructor
class UserControllerTest {

    UsersMocked user;

    @Test
    @DisplayName("Should")
    void findAllUsers() {
        assertEquals(2, 1 + 1);
    }

    @Test
    void findUserById() {
    }

    @Test
    void createUser() {

        var userData = user.returnUserDataMocked();
    }

    @Test
    void updateUser() {
    }

    @Test
    void deleteUser() {
    }
}
