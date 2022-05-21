package com.api.payments.messages;

public interface ItemMessages {
    //Success Messages
    String itemsEmpty = "Nenhum item cadastrado. ";
    String itemNotFound = "Item não encontrado. ";
    String itemCreated = "Item cadastrado com sucesso! ";
    String itemDataUpdated = "Dados do item atualizados com sucesso! ";
    String itemDataDeleted = "Dados do item deletados com sucesso! ";
    //Error Messages
    String itemNotCreated = "Não foi possível cadastrar o item. ";
    String itemAlreadyExists = "Item já cadastrado, tente inserir outros dados. ";
    String itemDataNotUpdated = "Não foi possível atualizar os dados do item. ";
    String itemDataNotDeleted = "Não foi possível deletar os dados do item. ";
    String badRequest = "Não foi possível realizar esta operação. ";
}
