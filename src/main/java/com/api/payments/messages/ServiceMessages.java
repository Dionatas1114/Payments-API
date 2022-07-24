package com.api.payments.messages;

public interface ServiceMessages {

    //Success Messages
    String noServiceDataRegistered = "Nenhum dado de serviço cadastrado. ";

    String serviceDataInserted = "Dados do serviço inseridos com sucesso! ";

    //Error Messages
    String serviceDataNotInserted = "Não foi possível inserir os dados do recebimento. ";
    String badRequest = "Não foi possível realizar esta operação. ";
}
