package com.api.payments.validations;

import org.sonatype.aether.RepositoryException;

public class UserValidator {

    public static void userValidator(String userName, String email, String password) throws Exception {

        boolean nameIsValid = Patterns.userNameValidate(userName);
        boolean emailIsValid = Patterns.emailValidate (email);
        boolean passwIsValid = Patterns.passwValidate(password);

        if (!nameIsValid) throw new RepositoryException(UserValidatorMessages.userNameInvalid);
        if (!emailIsValid) throw new RepositoryException (UserValidatorMessages.userEmailInvalid);
        if (!passwIsValid) throw new RepositoryException (UserValidatorMessages.userPasswInvalid);
    }
}
