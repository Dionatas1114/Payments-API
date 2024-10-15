package com.api.payments.validations;

import com.api.payments.utils.Log;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

import static com.api.payments.validations.Rules.*;

@Component
public class Patterns {

    public static boolean userNameValidate(String userName) {
        String userPattern = "(?i)(^[a-z])((?![? .,'-]$)[ .]?[a-z]){" + userNameMinLength + "," + userNameMaxLength + "}$";
        return Pattern.compile(userPattern).matcher(userName).matches();
    }

    public static boolean emailValidate(String email) {
        Log.warn("Validating User Email ...");
//        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        String emailPattern = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        return Pattern.compile(emailPattern).matcher(email).matches();
    }

    public static boolean passwordValidate(String password) {
        Log.warn("Validating User Password ...");
        String passwPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–{}:;',?/*~$^+=<>]).{" +
                passwMinLength + "," + passwMaxLength + "}$";
        return Pattern.compile(passwPattern).matcher(password).matches();
    }

    public static boolean phoneValidate(String phone) {
        Log.warn("Validating User telephone ...");
        String phonePattern = "^\\d{" + phoneLength + "}$";
        return Pattern.compile(phonePattern).matcher(phone).matches();
    }
}
