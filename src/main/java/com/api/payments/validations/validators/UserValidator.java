package com.api.payments.validations.validators;

import com.api.payments.validations.Patterns;
import org.hibernate.service.spi.ServiceException;

import static com.api.payments.validations.messages.UserValidatorMessages.*;

public class UserValidator {

    public static void userValidator(
            String userName,
            String email,
            String password,
            String phone) {

        boolean nameIsValid = Patterns.userNameValidate(userName);
        boolean emailIsValid = Patterns.emailValidate (email);
        boolean passwIsValid = Patterns.passwordValidate(password);
        boolean phoneValid = Patterns.phoneValidate(phone);

        if (!nameIsValid) throw new ServiceException (userNameInvalid);
        if (!emailIsValid) throw new ServiceException(userEmailInvalid);
        if (!passwIsValid) throw new ServiceException (userPasswInvalid);
        if (!phoneValid) throw new ServiceException (userPhoneInvalid);
    }
}
