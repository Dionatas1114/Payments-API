package com.api.payments.controller;

import com.api.payments.dto.DigitCodeDto;
import com.api.payments.dto.LoginDto;
import com.api.payments.dto.TokenDto;
import com.api.payments.exception.GenericExceptionHandler;
import com.api.payments.services.UserAccessService;
import com.api.payments.utils.Log;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.api.payments.messages.GenericMessages.*;
import static com.api.payments.messages.UserAccessMessages.userNotFound;

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
                            response = TokenDto.class),
                    @ApiResponse(code = 400, message = badRequest),
                    @ApiResponse(code = 401, message = unauthorized),
                    @ApiResponse(code = 404, message = userNotFound)
            })
    @PostMapping(path = {"/public/login"})
    public ResponseEntity<?> login(@Validated @RequestBody LoginDto loginDto) {

        try {
            TokenDto token = userAccessService.validateUserCredentials(loginDto);
//            response.addHeader("Authorization", "Bearer " + token);
            // TODO Implement Login: set o token no header
            return ResponseEntity.ok(token);
        } catch (Exception e) {
            return GenericExceptionHandler.getException(e);
        }
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
                            response = TokenDto.class),
                    @ApiResponse(code = 400, message = badRequest),
                    @ApiResponse(code = 401, message = unauthorized),
                    @ApiResponse(code = 404, message = "No Users Registered")
            })
    @PostMapping(path = {"/private/refresh_token"})
    public ResponseEntity<?> refresh_token() {

        try {
            Log.info("Refreshing Token...");
            // TODO Implement Refresh Token: verifica se o token antigo é válido e devolve um token novo
            return ResponseEntity.ok("Token Refreshed!");
        } catch (ExceptionInInitializerError e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return GenericExceptionHandler.getException(e);
        }
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
                            response = TokenDto.class),
                    @ApiResponse(code = 400, message = badRequest),
                    @ApiResponse(code = 401, message = unauthorized),
                    @ApiResponse(code = 404, message = "No Users Registered")
            })
    @DeleteMapping(path = {"/private/logout"})
    public ResponseEntity<?> logout() {

        try {
            Log.info("Logging Out...");
            // TODO Implement Logout: verifica se o token é valido e força a expiração
            return ResponseEntity.ok("Logged Out!");
        } catch (Exception e) {
            return GenericExceptionHandler.getException(e);
        }
    }

    @ApiOperation(
            value = "Returns Digit Code",
            notes = "This Request Returns the Digit Code",
            tags = {"UserAccess"})
    @ApiResponses(
            value = {
                    @ApiResponse(
                            code = 200,
                            message = "Returns Digit Code",
                            response = TokenDto.class),
                    @ApiResponse(code = 400, message = badRequest),
                    @ApiResponse(code = 401, message = unauthorized),
                    @ApiResponse(code = 404, message = "No Users Registered")
            })
    @PostMapping(path = {"/private/digit-code"})
    public ResponseEntity<?> digitCode(@Validated @RequestBody DigitCodeDto digitCodeDto) {

        try {
            TokenDto digitCode = userAccessService.getDigitCode(digitCodeDto);
            Log.info("Digit Code successfully generated!");
            return ResponseEntity.ok(digitCode);
        } catch (Exception e) {
            return GenericExceptionHandler.getException(e);
        }
    }
}
