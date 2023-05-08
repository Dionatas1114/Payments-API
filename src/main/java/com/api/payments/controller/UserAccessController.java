package com.api.payments.controller;

import com.api.payments.dto.DigitCodeDto;
import com.api.payments.dto.LoginDto;
import com.api.payments.dto.TokenDto;
import com.api.payments.services.UserAccessService;
import com.api.payments.utils.Log;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.api.payments.messages.GenericMessages.*;

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
                    @ApiResponse(code = 400, message = "Bad Request"),
                    @ApiResponse(code = 401, message = "Unauthorized Access"),
                    @ApiResponse(code = 404, message = "No Users Registered")
            })
    @PostMapping(path = {"/login"})
    public ResponseEntity<TokenDto> login(@Validated @RequestBody LoginDto loginDto){

        ResponseEntity result;

        try {
            TokenDto token = userAccessService.findUserByEmail(loginDto);
//            response.addHeader("Authorization", "Bearer " + token);
            result = new ResponseEntity<>(token, HttpStatus.OK);
        } catch (ExceptionInInitializerError e) {
            result = new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
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
                            response = TokenDto.class),
                    @ApiResponse(code = 400, message = "Bad Request"),
                    @ApiResponse(code = 401, message = "Unauthorized Access"),
                    @ApiResponse(code = 404, message = "No Users Registered")
            })
    @PostMapping(path = {"/refresh_token"})
    public ResponseEntity<TokenDto> refresh_token(){

        ResponseEntity result = null;

        try {
//            List<UsersDto> allUsers = userAccessService.findAllUsers();
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
                            response = TokenDto.class),
                    @ApiResponse(code = 400, message = "Bad Request"),
                    @ApiResponse(code = 401, message = "Unauthorized Access"),
                    @ApiResponse(code = 404, message = "No Users Registered")
            })
    @DeleteMapping(path = {"/logout"})
    public ResponseEntity<TokenDto> logout(){

        ResponseEntity result = null;

        try {
//            List<UsersDto> allUsers = userAccessService.findAllUsers();
//            result = new ResponseEntity<>(allUsers, HttpStatus.OK);
        } catch (ExceptionInInitializerError e) {
            result = new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            result = new ResponseEntity<>(badRequest, HttpStatus.BAD_REQUEST);
        }
        return result;
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
                    @ApiResponse(code = 400, message = "Bad Request"),
                    @ApiResponse(code = 401, message = "Unauthorized Access"),
                    @ApiResponse(code = 404, message = "No Users Registered")
            })
    @PostMapping(path = {"/digit-code"})
    public ResponseEntity<TokenDto> digitCode(@Validated @RequestBody DigitCodeDto digitCodeDto){

        ResponseEntity result;

        try {
            TokenDto digitCode = userAccessService.getDigitCode(digitCodeDto);
            Log.info("Digit Code successfully generated!");
            result = new ResponseEntity<>(digitCode, HttpStatus.OK);
        } catch (ExceptionInInitializerError e) {
            Log.error("Returns Digit Code: Error " + e.getMessage());
            result = new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            result = new ResponseEntity<>(badRequest, HttpStatus.BAD_REQUEST);
        }
        return result;
    }
}
