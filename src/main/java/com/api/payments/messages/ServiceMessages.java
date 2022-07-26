package com.api.payments.messages;

public interface ServiceMessages {

    //Success Messages
    String noServiceDataRegistered = "Nenhum dado de serviço cadastrado. ";
    String serviceDataNotFound = "Não foram encontrados os dados do serviço. ";
    String serviceDataInserted = "Dados do serviço inseridos com sucesso! ";
    String serviceDataUpdated = "Dados do serviço atualizados com sucesso! ";
    String serviceDataDeleted = "Dados do serviço excluídos com sucesso! ";
    //Error Messages
    String serviceDataNotInserted = "Não foi possível inserir os dados do serviço. ";
    String serviceDataNotUpdated = "Não foi possível atualizar os dados do serviço. ";
    String serviceDataNotDeleted = "Não foi possível excluir os dados do serviço. ";
    String badRequest = "Não foi possível realizar esta operação. ";
}
