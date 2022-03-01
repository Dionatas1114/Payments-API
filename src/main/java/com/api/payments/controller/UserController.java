package com.api.payments.controller;

import static com.api.payments.messages.UserMessages.*;
import com.api.payments.model.UserModel;
import com.api.payments.repository.UserRepository;
import com.api.payments.services.UserService;
import com.sun.istack.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

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
                result = new ResponseEntity<>(userRepository.findAll(), HttpStatus.OK);
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
            Optional<UserModel> userFind = userRepository.findById(userId);
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
    public Object createUser(@Validated @RequestBody UserModel userData) {
        logger.info("POST: /api/users");
        Object result;

        String name = userData.getName ();
        UserModel userModel = userRepository.findByName(name);
        boolean userNameAlreadyExists = Objects.equals(userModel.name, name);

        String email = userData.getEmail();
        UserModel user_model = userRepository.findByEmail(email);
        boolean userEmailAlreadyExists = Objects.equals(user_model.email, email);

        try {
            if (userNameAlreadyExists || userEmailAlreadyExists){
                result = new ResponseEntity<>(userAlreadyExists, HttpStatus.CONFLICT);
            } else {
                userService.saveUserData (userData);
                result = new ResponseEntity<>(userData, HttpStatus.CREATED);
            }
        } catch (Exception e) {
            result = new ResponseEntity<>(userAlreadyExists, HttpStatus.BAD_REQUEST);
        }
        return result;
    }

    @RequestMapping(path = {"api/users/{id}"}, method = RequestMethod.PUT)
    public ResponseEntity<String> updateUser(@Validated @PathVariable("id") UUID userId, @RequestBody UserModel userData){
        logger.info(String.format("UPDATE: /api/users/%s", userId));
        ResponseEntity<String> result;

        boolean userNotExists = !userRepository.existsById(userId);

        String name = userData.getName ();
        UserModel userModel = userRepository.findByName(name);
        boolean userNameAlreadyExists = Objects.equals(userModel.name, name);

        String email = userData.getEmail();
        UserModel user_model = userRepository.findByEmail(email);
        boolean userEmailAlreadyExists = Objects.equals(user_model.email, email);
        
        try {
            if (userNotExists){
                result = new ResponseEntity<>(userNotFound, HttpStatus.NOT_FOUND);
            } else if (userNameAlreadyExists || userEmailAlreadyExists){
                result = new ResponseEntity<>(userAlreadyExists, HttpStatus.CONFLICT);
            } else {
                userData.setId(userId);
                userService.saveUserData (userData);
                result = new ResponseEntity<>(userDataUpdated, HttpStatus.OK);
            }
        } catch (Exception e){
            result = new ResponseEntity<>(userDataNotUpdated, HttpStatus.BAD_REQUEST);
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
