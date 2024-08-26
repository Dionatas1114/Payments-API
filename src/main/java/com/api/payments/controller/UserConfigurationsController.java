package com.api.payments.controller;

import com.api.payments.dto.UserConfigurationsDto;
import com.api.payments.services.UserConfigurationsService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.api.payments.messages.UserMessages.noUserDataRegistered;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
@CrossOrigin("*")
public class UserConfigurationsController {

    UserConfigurationsService userConfigurationsService;

    @GetMapping(path = {"/usersConfigurations"})
    public ResponseEntity<?> findAllUsers(){
        try {
            List<UserConfigurationsDto> userConfigurations = userConfigurationsService.findUserConfigurations();

            if (userConfigurations.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(noUserDataRegistered);
            }

            return ResponseEntity.ok(userConfigurations);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
