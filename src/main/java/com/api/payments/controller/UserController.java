package com.api.payments.controller;

import com.api.payments.dto.UsersDto;
import com.api.payments.services.UserService;
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
@RequestMapping("/")
public class UserController {

    private UserService userService;

    @GetMapping(path = {"api/users"})
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

    @GetMapping(path = {"api/users/{id}"})
    public ResponseEntity<UsersDto> findUser(@PathVariable("id") UUID userId){

        ResponseEntity result;

        try {
            UsersDto userFind = userService.findOneUser(userId);
            result = new ResponseEntity<>(userFind, HttpStatus.OK);
        } catch (RepositoryException e){
            result = new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e){
            result = new ResponseEntity<>(badRequest, HttpStatus.BAD_REQUEST);
        }
        return result;
    }

    @PostMapping(path = {"api/users"})
    public ResponseEntity<UsersDto> createUser(@Validated @RequestBody UsersDto usersData) {

        ResponseEntity result;

        try {
            userService.saveUserData (usersData);
            result = new ResponseEntity<>(userCreated, HttpStatus.CREATED);
        } catch (ServiceException e) {
            result = new ResponseEntity<>(userNotCreated + e.getMessage(), HttpStatus.CONFLICT);
        } catch (Exception e) {
            result = new ResponseEntity<>(badRequest, HttpStatus.BAD_REQUEST);
        }
        return result;
    }

    @PutMapping(path = {"api/users/{id}"})
    public ResponseEntity<String> updateUser(@Validated @PathVariable("id") UUID userId, @RequestBody UsersDto usersData){

        ResponseEntity<String> result;
        
        try {
            userService.updateUserData (usersData, userId);
            result = new ResponseEntity<>(userDataUpdated, HttpStatus.OK);
        } catch (RepositoryException e){
            result = new ResponseEntity<>(userDataNotUpdated + e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (ServiceException e){
            result = new ResponseEntity<>(userDataNotUpdated + e.getMessage(), HttpStatus.CONFLICT);
        } catch (Exception e){
            result = new ResponseEntity<>(badRequest, HttpStatus.BAD_REQUEST);
        }
        return result;
    }

    @DeleteMapping(path = {"api/users/{id}"})
    public ResponseEntity<String> deleteUser(@PathVariable("id") UUID userId) {

        ResponseEntity<String> result;

        try {
            userService.deleteUserData(userId);
            result = new ResponseEntity<>(userDataDeleted, HttpStatus.OK);
        } catch (RepositoryException re) {
            result = new ResponseEntity<>(userDataNotDeleted + re.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            result = new ResponseEntity<>(badRequest, HttpStatus.BAD_REQUEST);
        }
        return result;
    }
}
