package com.api.payments.exception;

import com.api.payments.utils.Log;
import javassist.NotFoundException;
import org.hibernate.service.spi.ServiceException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.naming.AuthenticationException;

import static com.api.payments.messages.GenericMessages.badRequest;

public class GenericExceptionHandler {

    public static ResponseEntity<?> getException(Exception exception) {
        Log.error(exception.getMessage());
        return switch (exception) {
            case NotFoundException notFoundException ->
                    ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
            case ServiceException serviceException ->
                    ResponseEntity.status(HttpStatus.CONFLICT).body(exception.getMessage());
            case AuthenticationException authenticationException ->
                    ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(exception.getMessage());
            case InvalidDataAccessApiUsageException invalidDataAccessApiUsageException ->
                    ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
            default -> ResponseEntity.status(HttpStatus.BAD_REQUEST).body(badRequest);
        };
    }
}
