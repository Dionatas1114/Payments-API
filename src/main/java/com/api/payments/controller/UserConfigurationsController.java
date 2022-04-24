package com.api.payments.controller;

import com.api.payments.dto.UserConfigurationsDto;
import com.api.payments.repository.UserConfigurationsRepository;
import com.api.payments.services.UserConfigurationsService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/")
public class UserConfigurationsController {

    UserConfigurationsRepository userConfigurationsRepository;
    UserConfigurationsService userConfigurationsService;

    @GetMapping(path = {"api/usersConfigurations"})
    public ResponseEntity<List<UserConfigurationsDto>> findAllUsers(){
        ResponseEntity result;

        try {
            result = new ResponseEntity<>(userConfigurationsService.findUserConfigurations(), HttpStatus.OK);
        } catch (Exception e) {
            result = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return result;
    }
}
