package com.api.payments.controller;

import com.api.payments.repository.UserConfigurationsRepository;
import com.api.payments.services.UserConfigurationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class UserConfigurationsController {

    @Autowired
    UserConfigurationsRepository userConfigurationsRepository;

    @Autowired
    UserConfigurationsService userConfigurationsService;

    @RequestMapping(path = {"api/usersConfigurations"}, method = RequestMethod.GET)
    public Object findAllUsers(){
        Object result = new Object();

        try {
            result = new ResponseEntity<>(userConfigurationsService.findUserConfigurations(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
