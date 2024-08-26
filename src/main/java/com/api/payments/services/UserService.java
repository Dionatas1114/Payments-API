package com.api.payments.services;

import com.api.payments.dto.UsersDto;

import java.util.List;
import java.util.UUID;

public interface UserService {

    List<UsersDto> findAllUsers() throws Exception;
    UsersDto findUserById(UUID userId) throws Exception;
    void saveUserData(UsersDto userDto) throws Exception;
    void updateUserData(UUID userId, UsersDto usersData) throws Exception;
    void deleteUserData(UUID userId) throws Exception;
}
