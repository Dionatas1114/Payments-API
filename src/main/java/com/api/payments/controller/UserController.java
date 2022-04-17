package com.api.payments.controller;

import com.api.payments.entity.Users;
import com.api.payments.repository.UserRepository;
import com.api.payments.services.UserService;
import com.sun.istack.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import static com.api.payments.messages.UserMessages.*;

@RestController
@RequestMapping("/")
public class UserController {

    private Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @RequestMapping(path = {"api/users"}, method = RequestMethod.GET)
    public Object findAllUsers(){
        logger.info("GET: /api/users");
        Object result;

        try {
            if (userRepository.count() == 0){
                result = new ResponseEntity<>(usersEmpty, HttpStatus.NOT_FOUND);
            } else {
                result = new ResponseEntity<>(userService.findAllUsers(), HttpStatus.OK);
            }
        } catch (Exception e) {
            result = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
            e.printStackTrace();
        }
        return result;
    }

    @RequestMapping(path = {"api/users/{id}"}, method = RequestMethod.GET)
    public Object findUser(@PathVariable("id") UUID userId){
        logger.info(String.format("GET: /api/users/%s", userId));
        Object result;

        try {
            Optional<Users> userFind = userRepository.findById(userId);
            if (userFind.isPresent ()){
                result = new ResponseEntity<>(userFind.get(), HttpStatus.OK);
            } else {
                result = new ResponseEntity<>(userNotFound, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e){
            result = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
            e.printStackTrace();
        }
        return result;
    }

    @RequestMapping(path = {"api/users"}, method = RequestMethod.POST)
    public Object createUser(@Validated @RequestBody Users usersData) {
        logger.info("POST: /api/users");
        Object result;

        try {
            userService.saveUserData (usersData);
            result = new ResponseEntity<>(usersData, HttpStatus.CREATED);
        } catch (Exception e) {
            result = new ResponseEntity<>(userNotCreated + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return result;
    }

    @RequestMapping(path = {"api/users/{id}"}, method = RequestMethod.PUT)
    public ResponseEntity<String> updateUser(@Validated @PathVariable("id") UUID userId, @RequestBody Users usersData){
        logger.info(String.format("UPDATE: /api/users/%s", userId));
        ResponseEntity<String> result;

        boolean userNotExists = !userRepository.existsById(userId);

        String name = usersData.getName ();
        Users usersModel = userRepository.findByName(name);
        boolean userNameAlreadyExists = Objects.equals(usersModel.name, name);

        String email = usersData.getEmail();
        Users users_model = userRepository.findByEmail(email);
        boolean userEmailAlreadyExists = Objects.equals(users_model.email, email);
        
        try {
            userService.updateUserData (usersData, userId);
            result = new ResponseEntity<>(userDataUpdated, HttpStatus.OK);
        } catch (Exception e){
            result = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
            e.printStackTrace();
        }
        return result;
    }

    @RequestMapping(path = {"api/users/{id}"}, method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteUser(@PathVariable("id") UUID userId) {
        logger.info(String.format("DELETE: /api/users/%s", userId));
        ResponseEntity<String> result;

        try {
            if (!userRepository.existsById(userId)) {
                result = new ResponseEntity<>(userNotFound, HttpStatus.NOT_FOUND);
            } else {
                userRepository.deleteById(userId);
                result = new ResponseEntity<>(userDataDeleted, HttpStatus.OK);
            }
        } catch (Exception e) {
            result = new ResponseEntity<>(userDataNotDeleted, HttpStatus.BAD_REQUEST);
            e.printStackTrace();
        }
        return result;
    }
}
