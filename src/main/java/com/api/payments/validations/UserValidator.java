package com.api.payments.validations;

public class UserValidator {

    public static void userValidator(String userName, String email, String password) throws Exception {

        boolean nameIsValid = Patterns.userNameValidate(userName);
        boolean emailIsValid = Patterns.emailValidate (email);
        boolean passwIsValid = Patterns.passwValidate(password);

        if (!nameIsValid) throw new Exception (UserValidatorMessages.userNameInvalid);
        if (!emailIsValid) throw new Exception (UserValidatorMessages.userEmailInvalid);
        if (!passwIsValid) throw new Exception (UserValidatorMessages.userPasswInvalid);
    }
}
