package com.api.payments.controller;

import com.api.payments.dto.UserConfigurationsDto;
import com.api.payments.services.UserConfigurationsService;
import lombok.AllArgsConstructor;
import org.sonatype.aether.RepositoryException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class UserConfigurationsController {

    UserConfigurationsService userConfigurationsService;

    @GetMapping(path = {"/usersConfigurations"})
    public ResponseEntity<List<UserConfigurationsDto>> findAllUsers(){
        ResponseEntity result;

        try {
            List<UserConfigurationsDto> userConfigurations =
                    userConfigurationsService.findUserConfigurations();
            result = new ResponseEntity<>(userConfigurations, HttpStatus.OK);
        } catch (RepositoryException e) {
            result = new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            result = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return result;
    }
}
