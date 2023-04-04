package com.api.payments.controller;

import com.api.payments.dto.LoginDto;
import com.api.payments.dto.TokenDto;
import com.api.payments.dto.UsersDto;
import com.api.payments.services.UserAccessService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.api.payments.messages.UserMessages.badRequest;

@RestController
@AllArgsConstructor
@RequestMapping("/auth")
@CrossOrigin("*")
public class UserAccessController {

    UserAccessService userAccessService;

    @ApiOperation(
            value = "Returns User Token",
            notes = "This Request Returns the User Token",
            tags = {"UserAccess"})
    @ApiResponses(
            value = {
                    @ApiResponse(
                            code = 200,
                            message = "Returns User Token",
                            response = UsersDto.class),
                    @ApiResponse(code = 400, message = "Bad Request"),
                    @ApiResponse(code = 401, message = "Unauthorized Access"),
                    @ApiResponse(code = 404, message = "No Users Registered")
            })
    @PostMapping(path = {"/login"})
    public ResponseEntity<TokenDto> login(@Validated @RequestBody LoginDto loginDto){

        ResponseEntity result = null;

        try {
            TokenDto token = userAccessService.findUserByEmail(loginDto);
//            response.addHeader("Authorization", "Bearer " + token);
            result = new ResponseEntity<>(token, HttpStatus.OK);
        } catch (ExceptionInInitializerError e) {
            result = new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            result = new ResponseEntity<>(badRequest, HttpStatus.BAD_REQUEST);
        }
        return result;
    }

    @ApiOperation(
            value = "Returns User Token",
            notes = "This Request Returns the User Token",
            tags = {"UserAccess"})
    @ApiResponses(
            value = {
                    @ApiResponse(
                            code = 200,
                            message = "Returns User Token",
                            response = UsersDto.class),
                    @ApiResponse(code = 400, message = "Bad Request"),
                    @ApiResponse(code = 401, message = "Unauthorized Access"),
                    @ApiResponse(code = 404, message = "No Users Registered")
            })
    @PostMapping(path = {"/refresh_token"})
    public ResponseEntity<List<UsersDto>> refresh_token(){

        ResponseEntity result = null;

        try {
//            List<UsersDto> allUsers = userService.findAllUsers();
//            result = new ResponseEntity<>(allUsers, HttpStatus.OK);
        } catch (ExceptionInInitializerError e) {
            result = new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            result = new ResponseEntity<>(badRequest, HttpStatus.BAD_REQUEST);
        }
        return result;
    }

    @ApiOperation(
            value = "Returns User Token",
            notes = "This Request Returns the User Token",
            tags = {"UserAccess"})
    @ApiResponses(
            value = {
                    @ApiResponse(
                            code = 200,
                            message = "Returns User Token",
                            response = UsersDto.class),
                    @ApiResponse(code = 400, message = "Bad Request"),
                    @ApiResponse(code = 401, message = "Unauthorized Access"),
                    @ApiResponse(code = 404, message = "No Users Registered")
            })
    @DeleteMapping(path = {"/logout"})
    public ResponseEntity<List<UsersDto>> logout(){

        ResponseEntity result = null;

        try {
//            List<UsersDto> allUsers = userService.findAllUsers();
//            result = new ResponseEntity<>(allUsers, HttpStatus.OK);
        } catch (ExceptionInInitializerError e) {
            result = new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            result = new ResponseEntity<>(badRequest, HttpStatus.BAD_REQUEST);
        }
        return result;
    }
}
