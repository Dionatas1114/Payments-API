package com.api.payments.validations;

import org.springframework.stereotype.Component;
import java.util.regex.Pattern;

import static com.api.payments.validations.Rules.*;

@Component
public class Patterns {

    public static boolean userNameValidate(String userName) {
        String userPattern = "(?i)(^[a-z])((?![? .,'-]$)[ .]?[a-z]){"+ userNameMinLength +","+ userNameMaxLength +"}$";
        return Pattern.compile(userPattern).matcher(userName).matches ();
    }

    public static boolean emailValidate(String email) {
        String emailPattern = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        return Pattern.compile(emailPattern).matcher(email).matches ();
    }

    public static boolean passwValidate(String password) {
        String passwPattern =
                "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{"+ passwMinLength +","+ passwMaxLength +"}$";
        return Pattern.compile (passwPattern).matcher(password).matches ();
    }

    public static boolean phoneValidate(String phone) {
        String phonePattern = "^\\d{" + phoneLength + "}$";
        return Pattern.compile (phonePattern).matcher(phone).matches ();
    }
}
