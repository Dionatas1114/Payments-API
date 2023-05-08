package com.api.payments.messages;

public interface UserAccessMessages {

    // Process Messages
    String tokenValidation = "Validating Token... ";
    String tokenGenerate = "Generating Token... valid for ";

    // Process Messages
    String tokenIsValid = "The Token is Valid! ";

    // Error Messages
    String invalidToken = "The Token is Invalid! ";
    String invalidPassword = "Access denied: incorrect password. ";
    String userNotFound = "User not found. ";
    String invalidEmail = "Invalid Email. ";

}
