package com.api.payments.config;

import com.api.payments.utils.Log;

import java.util.Objects;

public class CheckEnvVars {

    public static void checkEnvVarsIsNotNull(
            String envVarType,
            String[] envVarValue,
            String[] envVarName){

        Log.debug(envVarType + ": Starting verification...");

        for (int i = 0; i < envVarValue.length; i++) {
            message(envVarValue[i], envVarName[i]);
        }
    }

    public static void message(String key, String varName){

        String empty = "";
        boolean isValueNotEmpty = !Objects.equals(key, empty);

        if (isValueNotEmpty) {
            Log.info(varName + ": OK ✔");
        } else {
            Log.error(varName + ": NULL ❌");
        }
    }
}
