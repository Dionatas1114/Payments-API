package com.api.payments.messages;

public interface UserAccessMessages {

    // Process Messages
    String tokenValidation = "Validando Token... ";
    String tokenGenerate = "Gerando Token... válido por ";

    // Process Messages
    String tokenIsValid = "Token Valido! ";

    // Error Messages
    String invalidToken = "Token Inválido! ";
    String invalidPassword = "Acesso Negado: Senha incorreta. ";
    String invalidEmail = "Usuário não encontrado. ";

}
