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
        if (exception instanceof NotFoundException) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
        } else if (exception instanceof ServiceException) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(exception.getMessage());
        } else if (exception instanceof AuthenticationException) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(exception.getMessage());
        } else if (exception instanceof InvalidDataAccessApiUsageException) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(badRequest);
    }
}
