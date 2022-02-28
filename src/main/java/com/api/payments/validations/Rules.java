package com.api.payments.validations;

import org.springframework.stereotype.Component;

@Component
public class Rules {

    public static int userNameMinLength = 6;
    public static int userNameMaxLength = 50;

    public static int emailMinLength = 6;
    public static int emailMaxLength = 30;

    public static int passwMinLength = 6;
    public static int passwMaxLength = 30;

}
