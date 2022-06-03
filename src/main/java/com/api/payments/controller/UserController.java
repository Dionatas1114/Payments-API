package com.api.payments.controller;

import com.api.payments.dto.UsersDto;
import com.api.payments.services.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.hibernate.service.spi.ServiceException;
import org.sonatype.aether.RepositoryException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static com.api.payments.messages.UserMessages.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
@CrossOrigin("*")
public class UserController {

    private UserService userService;

    @ApiOperation(
            value = "Returns Data from all Users",
            notes = "This Request Data from all Users in the Database",
            tags = {"Users"})
    @ApiResponses(
            value = {
                    @ApiResponse(
                            code = 200,
                            message = "Return All Users",
                            response = UsersDto.class),
                    @ApiResponse(code = 400, message = "Bad Request"),
                    @ApiResponse(code = 401, message = "Unauthorized Access"),
                    @ApiResponse(code = 404, message = "No Registered User")
            })
    @GetMapping(path = {"/users"})
    public ResponseEntity<List<UsersDto>> findAllUsers(){

        ResponseEntity result;

        try {
            List<UsersDto> allUsers = userService.findAllUsers();
            result = new ResponseEntity<>(allUsers, HttpStatus.OK);
        } catch (RepositoryException e) {
            result = new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            result = new ResponseEntity<>(badRequest, HttpStatus.BAD_REQUEST);
        }
        return result;
    }

    @ApiOperation(
            value = "Return User Data by Id",
            notes = "This Request Return User Data in the Database",
            tags = {"Users"})
    @ApiResponses(
            value = {
                    @ApiResponse(
                            code = 200,
                            message = "Return User Data",
                            response = UsersDto.class),
                    @ApiResponse(code = 400, message = "Bad Request"),
                    @ApiResponse(code = 401, message = "Unauthorized Access"),
                    @ApiResponse(code = 404, message = "No Registered User")
            })
    @GetMapping(path = {"/users/{id}"})
    public ResponseEntity<UsersDto> findUserById(
            @PathVariable("id") UUID userId){

        ResponseEntity result;

        try {
            UsersDto user = userService.findUserById(userId);
            result = new ResponseEntity<>(user, HttpStatus.OK);
        } catch (RepositoryException e){
            result = new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e){
            result = new ResponseEntity<>(badRequest, HttpStatus.BAD_REQUEST);
        }
        return result;
    }

    @ApiOperation(
            value = "Register User Data",
            notes = "This Request Register User Data in the Database",
            tags = {"Users"})
    @ApiResponses(
            value = {
                    @ApiResponse(
                            code = 201,
                            message = "Register User Data",
                            response = void.class),
                    @ApiResponse(code = 400, message = "Bad Request"),
                    @ApiResponse(code = 401, message = "Unauthorized Access"),
                    @ApiResponse(code = 404, message = "No Registered User"),
                    @ApiResponse(code = 409, message = "Conflict")
            })
    @PostMapping(path = {"/users"})
    public ResponseEntity<UsersDto> createUser(
            @Validated @RequestBody UsersDto usersData) {

        ResponseEntity result;

        try {
            userService.saveUserData (usersData);
            result = new ResponseEntity<>(userCreated, HttpStatus.CREATED);
        } catch (ServiceException e) {
            result = new ResponseEntity<>(
                    userNotCreated + e.getMessage(), HttpStatus.CONFLICT);
        } catch (Exception e) {
            result = new ResponseEntity<>(badRequest, HttpStatus.BAD_REQUEST);
        }
        return result;
    }

    @ApiOperation(
            value = "Update User Data",
            notes = "This Request Update User Data in the Database",
            tags = {"Users"})
    @ApiResponses(
            value = {
                    @ApiResponse(
                            code = 200,
                            message = "Update User Data",
                            response = void.class),
                    @ApiResponse(code = 400, message = "Bad Request"),
                    @ApiResponse(code = 401, message = "Unauthorized Access"),
                    @ApiResponse(code = 404, message = "No Registered User"),
                    @ApiResponse(code = 409, message = "Conflict")
            })
    @PutMapping(path = {"/users/{id}"})
    public ResponseEntity<String> updateUser(
            @Validated @PathVariable("id") UUID userId,
            @RequestBody UsersDto usersData){

        ResponseEntity<String> result;
        
        try {
            userService.updateUserData (usersData, userId);
            result = new ResponseEntity<>(userDataUpdated, HttpStatus.OK);
        } catch (RepositoryException e){
            result = new ResponseEntity<>(
                    userDataNotUpdated + e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (ServiceException e){
            result = new ResponseEntity<>(
                    userDataNotUpdated + e.getMessage(), HttpStatus.CONFLICT);
        } catch (Exception e){
            result = new ResponseEntity<>(badRequest, HttpStatus.BAD_REQUEST);
        }
        return result;
    }

    @ApiOperation(
            value = "Delete User Data",
            notes = "This Request Delete User Data in the Database",
            tags = {"Users"})
    @ApiResponses(
            value = {
                    @ApiResponse(
                            code = 200,
                            message = "Delete User Data",
                            response = void.class),
                    @ApiResponse(code = 400, message = "Bad Request"),
                    @ApiResponse(code = 401, message = "Unauthorized Access"),
                    @ApiResponse(code = 404, message = "No Registered User")
            })
    @DeleteMapping(path = {"/users/{id}"})
    public ResponseEntity<String> deleteUser(@PathVariable("id") UUID userId) {

        ResponseEntity<String> result;

        try {
            userService.deleteUserData(userId);
            result = new ResponseEntity<>(userDataDeleted, HttpStatus.OK);
        } catch (RepositoryException re) {
            result = new ResponseEntity<>(
                    userDataNotDeleted + re.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            result = new ResponseEntity<>(badRequest, HttpStatus.BAD_REQUEST);
        }
        return result;
    }
}
