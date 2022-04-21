package com.api.payments.services;

import com.api.payments.dto.UsersDto;

import java.util.List;
import java.util.UUID;

public interface UserService {

    List<UsersDto> findAllUsers() throws Exception;
    UsersDto findOneUser(UUID userId) throws Exception;
    void saveUserData(UsersDto userDto) throws Exception;
    void updateUserData(UsersDto usersData, UUID userId) throws Exception;
    void deleteUserData(UUID userId) throws Exception;

}
