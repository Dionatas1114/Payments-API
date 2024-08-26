package com.api.payments.controller;

import com.api.payments.dto.UserConfigurationsDto;
import com.api.payments.exception.GenericExceptionHandler;
import com.api.payments.services.UserConfigurationsService;
import lombok.AllArgsConstructor;
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
    public ResponseEntity<?> findAllUsers() {

        try {
            List<UserConfigurationsDto> userConfigurations = userConfigurationsService.findUserConfigurations();
            return ResponseEntity.ok(userConfigurations);
        } catch (Exception e) {
            return GenericExceptionHandler.getException(e);
        }
    }
}
