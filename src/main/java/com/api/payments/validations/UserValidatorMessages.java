package com.api.payments.validations;

import static com.api.payments.validations.Rules.*;

public interface UserValidatorMessages {

    String userNameInvalid = "Nome do usuário está ausente ou é inválido";
    String userEmailInvalid = "Email do usuário deve conter de "+ emailMinLength +" a "+ emailMaxLength
            +" caracteres, ex: pedro@outlook.com";
    String userPasswInvalid = "Senha deve conter de "+passwMinLength+" a "+passwMaxLength
            +" caracteres, sendo ao menos 1 letra maiúscula, símbolos e caracteres especiais";

}
