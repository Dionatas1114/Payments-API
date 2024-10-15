package com.api.payments.messages;

public interface UserMessages {

    //Success Messages
    String noUserDataRegistered = "Nenhum dado de usuário cadastrado. ";
    String userDataNotFound = "Não foram encontrados os dados do usuário. ";
    String userDataInserted = "Dados do usuário inseridos com sucesso! ";
    String userDataUpdated = "Dados do usuário atualizados com sucesso! ";
    String userDataDeleted = "Dados do usuário deletados com sucesso! ";

    //Error Messages
    String userDataNotInserted = "Não foi possível inserir os dados do usuário. ";
    String userNameAlreadyRegistered = "Dados do usuário já existentes, tente inserir dados diferentes. ";
    String userEmailAlreadyRegistered = "Já existe um usuário cadastrado com este email, tente inserir dados diferentes. ";
    String userPhoneAlreadyRegistered = "Já existe um usuário cadastrado com este número de telefone, tente inserir dados diferentes. ";
    String userDataNotUpdated = "Não foi possível atualizar os dados do usuário. ";
    String userDataNotDeleted = "Não foi possível deletar os dados do usuário. ";

}
