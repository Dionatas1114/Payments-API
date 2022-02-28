package com.api.payments.validations;

import org.springframework.stereotype.Component;
import java.util.regex.Pattern;

import static com.api.payments.validations.Rules.*;

@Component
public class Patterns {

    public static boolean userNameValidate(String email) {
        Pattern emailPattern = Pattern.compile (
                "(?i)(^[a-z])((?![? .,'-]$)[ .]?[a-z]){"+userNameMinLength+","+userNameMaxLength+"}$");
        return emailPattern.matcher(email).matches ();
    }

    public static boolean emailValidate(String email) {
        Pattern emailPattern = Pattern.compile (
                "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$");
        return emailPattern.matcher(email).matches ();
    }

    public static boolean passwValidate(String email) {
        Pattern emailPattern = Pattern.compile (
                "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{"+passwMinLength+","+passwMaxLength+"}$");
        return emailPattern.matcher(email).matches ();
    }
}
