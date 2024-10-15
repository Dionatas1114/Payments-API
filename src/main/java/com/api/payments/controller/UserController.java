package com.api.payments.controller;

import com.api.payments.dto.UserConfigurationsDto;
import com.api.payments.dto.UsersDto;
import com.api.payments.exception.GenericExceptionHandler;
import com.api.payments.services.UserConfigurationsService;
import com.api.payments.services.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

import static com.api.payments.messages.GenericMessages.badRequest;
import static com.api.payments.messages.GenericMessages.unauthorized;
import static com.api.payments.messages.UserMessages.*;


@RestController
@AllArgsConstructor
@RequestMapping("/api")
@CrossOrigin("*")
public class UserController {

    private UserService userService;
    private UserConfigurationsService userConfigurationsService;

    @ApiOperation(
            value = "Returns Data from all Users",
            notes = "This Request Returns all User Data from the Database",
            tags = {"Users"})
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Return All User Data", response = UsersDto.class),
                    @ApiResponse(code = 400, message = badRequest),
                    @ApiResponse(code = 401, message = unauthorized),
                    @ApiResponse(code = 404, message = noUserDataRegistered)
            })
    @GetMapping(path = {"/private/users"})
    public ResponseEntity<?> findAllUsers() {

        try {
            List<UsersDto> allUsers = userService.findAllUsers();
            return ResponseEntity.ok(allUsers);
        } catch (Exception e) {
            return GenericExceptionHandler.getException(e);
        }
    }

    @ApiOperation(
            value = "Returns User Data by Id",
            notes = "This Request Returns User Data from the Database",
            tags = {"Users"})
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Returns User Data", response = UsersDto.class),
                    @ApiResponse(code = 400, message = badRequest),
                    @ApiResponse(code = 401, message = unauthorized),
                    @ApiResponse(code = 404, message = userDataNotFound)
            })
    @GetMapping(path = {"/private/users/{id}"})
    public ResponseEntity<?> findUserById(@PathVariable("id") UUID userId) {

        try {
            UsersDto user = userService.findUserById(userId);
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            return GenericExceptionHandler.getException(e);
        }
    }

    @ApiOperation(
            value = "Register User Data",
            notes = "This Request Register User Data in the Database",
            tags = {"Users"})
    @ApiResponses(
            value = {
                    @ApiResponse(code = 201, message = userDataInserted),
                    @ApiResponse(code = 400, message = badRequest),
                    @ApiResponse(code = 401, message = unauthorized),
                    @ApiResponse(code = 409, message = "Conflict")
            })
    @PostMapping(path = {"/public/users"})
    public ResponseEntity<?> createUser(@Validated @RequestBody UsersDto usersData) {

        try {
            userService.saveUserData(usersData);
            return ResponseEntity.status(HttpStatus.CREATED).body(userDataInserted);
        } catch (Exception e) {
            return GenericExceptionHandler.getException(e);
        }
    }

    @ApiOperation(
            value = "Update User Data",
            notes = "This Request Update User Data in the Database",
            tags = {"Users"})
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = userDataUpdated),
                    @ApiResponse(code = 400, message = badRequest),
                    @ApiResponse(code = 401, message = unauthorized),
                    @ApiResponse(code = 404, message = userDataNotFound),
                    @ApiResponse(code = 409, message = "Conflict")
            })
    @PutMapping(path = {"/private/users/{id}"})
    public ResponseEntity<?> updateUser(
            @Validated @PathVariable("id") UUID userId,
            @Validated @RequestBody UsersDto usersData) {

        try {
            userService.updateUserData(userId, usersData);
            return ResponseEntity.ok(userDataUpdated);
        } catch (Exception e) {
            return GenericExceptionHandler.getException(e);
        }
    }

    @ApiOperation(
            value = "Delete User Data",
            notes = "This Request Delete User Data in the Database",
            tags = {"Users"})
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = userDataDeleted),
                    @ApiResponse(code = 400, message = badRequest),
                    @ApiResponse(code = 401, message = unauthorized),
                    @ApiResponse(code = 404, message = userDataNotFound)
            })
    @DeleteMapping(path = {"/private/users/{id}"})
    public ResponseEntity<?> deleteUser(@PathVariable("id") UUID userId) {

        try {
            userService.deleteUserData(userId);
            return ResponseEntity.ok(userDataDeleted);
        } catch (Exception e) {
            return GenericExceptionHandler.getException(e);
        }
    }

    @ApiOperation(
            value = "Returns Data from all User Configurations",
            notes = "This Request Returns all User Configurations Data from the Database",
            tags = {"Users"})
    @ApiResponses(
            value = {
                    @ApiResponse(
                            code = 200,
                            message = "Return All User Configurations Data",
                            response = UserConfigurationsDto.class),
                    @ApiResponse(code = 400, message = badRequest),
                    @ApiResponse(code = 401, message = unauthorized),
                    @ApiResponse(code = 404, message = noUserDataRegistered)
            })
    @GetMapping(path = {"/private/usersConfigurations"})
    public ResponseEntity<?> findAllUserConfigurations() {

        try {
            List<UserConfigurationsDto> userConfigurations = userConfigurationsService.findUserConfigurations();
            return ResponseEntity.ok(userConfigurations);
        } catch (Exception e) {
            return GenericExceptionHandler.getException(e);
        }
    }
}
