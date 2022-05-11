package com.api.payments.validations;

public class UserValidator {

    public static void userValidator(String userName, String email, String password) throws Exception {

        boolean nameIsValid = Patterns.userNameValidate(userName);
        boolean emailIsValid = Patterns.emailValidate (email);
        boolean passwIsValid = Patterns.passwValidate(password);

        if (!nameIsValid) throw new ExceptionInInitializerError (UserValidatorMessages.userNameInvalid);
        if (!emailIsValid) throw new ExceptionInInitializerError (UserValidatorMessages.userEmailInvalid);
        if (!passwIsValid) throw new ExceptionInInitializerError (UserValidatorMessages.userPasswInvalid);

    }
}
