package com.api.payments.validations.validators;

import com.api.payments.validations.Patterns;
import com.api.payments.validations.messages.UserValidatorMessages;
import org.hibernate.service.spi.ServiceException;

public class UserValidator {

    public static void userValidator(String userName, String email, String password, String phone) {

        boolean nameIsValid = Patterns.userNameValidate(userName);
        boolean emailIsValid = Patterns.emailValidate (email);
        boolean passwIsValid = Patterns.passwValidate(password);
        boolean phoneValid = Patterns.phoneValidate(phone);

        if (!nameIsValid) throw new ServiceException (UserValidatorMessages.userNameInvalid);
        if (!emailIsValid) throw new ServiceException(UserValidatorMessages.userEmailInvalid);
        if (!passwIsValid) throw new ServiceException (UserValidatorMessages.userPasswInvalid);
        if (!phoneValid) throw new ServiceException (UserValidatorMessages.userPhoneInvalid);
    }
}
