package com.api.payments.messages;

public interface UserMessages {
    //Success Messages
    String usersEmpty = "Nenhum usuário cadastrado. ";
    String userNotFound = "Usuário não encontrado. ";
    String userCreated = "Usuário cadastrado com sucesso! ";
    String userDataUpdated = "Dados do usuário atualizados com sucesso! ";
    String userDataDeleted = "Dados do usuário deletados com sucesso! ";
    //Error Messages
    String userNotCreated = "Não foi possível cadastrar o usuário, tente mais tarde. ";
    String userAlreadyExists = "Usuário já cadastrado, tente inserir outros dados. ";
    String userDataNotUpdated = "Não foi possível atualizar os dados do usuário, tente mais tarde. ";
    String userDataNotDeleted = "Não foi possível deletar os dados do usuário, tente mais tarde. ";

}
