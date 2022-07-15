package com.api.payments.messages;

public interface ProductMessages {
    //Success Messages
    String noProductDataRegistered = "Nenhum dado de produto cadastrado. ";
    String productDataNotFound = "Não foram encontrados os dados do produto. ";
    String productDataInserted = "Dados do produto inseridos com sucesso! ";
    String productDataUpdated = "Dados do produto atualizados com sucesso! ";
    String productDataDeleted = "Dados do produto excluídos com sucesso! ";
    //Error Messages
    String productDataNotInserted = "Não foi possível inserir os dados do produto. ";
    String productDataAlreadyExists = "Dados do produto já existentes, tente inserir outros dados. ";
    String productDataNotUpdated = "Não foi possível atualizar os dados do produto. ";
    String productDataNotDeleted = "Não foi possível excluir os dados do produto. ";
    String badRequest = "Não foi possível realizar esta operação. ";

}
