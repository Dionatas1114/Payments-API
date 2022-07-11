package com.api.payments.messages;

public interface UserMessages {
    //Success Messages
    String usersEmpty = "Nenhum usuário cadastrado. ";
    String userNotFound = "Usuário não encontrado. ";
    String userCreated = "Usuário cadastrado com sucesso! ";
    String userDataUpdated = "Dados do usuário atualizados com sucesso! ";
    String userDataDeleted = "Dados do usuário deletados com sucesso! ";
    //Error Messages
    String userNotCreated = "Não foi possível cadastrar o usuário. ";
    String userNameAlreadyRegistered = "Já existe um usuário cadastrado com este nome, tente inserir outros dados. ";
    String userEmailAlreadyRegistered = "Já existe um usuário cadastrado com este email, tente inserir outros dados. ";
    String userPhoneAlreadyRegistered = "Já existe um usuário cadastrado com este número de telefone, tente inserir outros dados. ";
    String userDataNotUpdated = "Não foi possível atualizar os dados do usuário. ";
    String userDataNotDeleted = "Não foi possível deletar os dados do usuário. ";
    String badRequest = "Não foi possível realizar esta operação. ";

}
