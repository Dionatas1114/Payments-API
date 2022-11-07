package com.api.payments.validations.validators;

import com.api.payments.utils.Log;

import java.util.ArrayList;
import java.util.Objects;

public class WhatsAppValidator {

    public static void isValidKey(String key){
        String empty = "";
        boolean keyIsEmpty = Objects.equals(key, empty);
        boolean keyIsNull = Objects.equals(key, null);

        if (keyIsEmpty || keyIsNull){
            Log.error(key + " is Empty or Null ❌");
            throw new Error(key + " is Empty or Null");
        }
    }

    public static void whatsAppValidate(
            String route,
            String method,
            String body
    ){

        ArrayList<String> list = new ArrayList<>();
        list.add(route);
        list.add(method);
        list.add(body);

        list.forEach(WhatsAppValidator::isValidKey);
    }
}
