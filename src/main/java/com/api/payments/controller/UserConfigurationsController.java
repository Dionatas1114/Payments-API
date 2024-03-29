package com.api.payments.controller;

import com.api.payments.dto.UserConfigurationsDto;
import com.api.payments.services.UserConfigurationsService;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
@CrossOrigin("*")
public class UserConfigurationsController {

    UserConfigurationsService userConfigurationsService;

    @GetMapping(path = {"/usersConfigurations"})
    public ResponseEntity<List<UserConfigurationsDto>> findAllUsers(){
        ResponseEntity result;

        try {
            val userConfigurations = userConfigurationsService.findUserConfigurations();
            result = new ResponseEntity<>(userConfigurations, HttpStatus.OK);
        } catch (ExceptionInInitializerError e) {
            result = new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            result = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return result;
    }
}
