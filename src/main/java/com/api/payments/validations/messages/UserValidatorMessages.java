package com.api.payments.validations.messages;

import static com.api.payments.validations.Rules.*;

public interface UserValidatorMessages {

    String userCannotBeNull = "O Usuário não pode ser Nulo";
    String userNameInvalid = "O Nome do usuário está ausente ou é inválido. Deve conter de "+ userNameMinLength
            +" a "+ userNameMaxLength +" caracteres.";
    String userEmailInvalid = "O Email do usuário deve conter de "+ emailMinLength +" a "+ emailMaxLength
            +" caracteres, ex: pedro@outlook.com.";
    String userPasswInvalid = "A Senha deve conter de "+ passwMinLength +" a "+ passwMaxLength
            +" caracteres, sendo ao menos 1 letra maiúscula, símbolos e caracteres especiais.";
    String userPhoneInvalid = "O Telefone deve conter "+ phoneLength +" dígitos numéricos.";
}
