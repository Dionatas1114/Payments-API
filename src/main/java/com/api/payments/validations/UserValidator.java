package com.api.payments.validations;

import org.hibernate.service.spi.ServiceException;

public class UserValidator {

    public static void userValidator(String userName, String email, String password) {

        boolean nameIsValid = Patterns.userNameValidate(userName);
        boolean emailIsValid = Patterns.emailValidate (email);
        boolean passwIsValid = Patterns.passwValidate(password);

        if (!nameIsValid) throw new ServiceException (UserValidatorMessages.userNameInvalid);
        if (!emailIsValid) throw new ServiceException(UserValidatorMessages.userEmailInvalid);
        if (!passwIsValid) throw new ServiceException (UserValidatorMessages.userPasswInvalid);

    }
}
