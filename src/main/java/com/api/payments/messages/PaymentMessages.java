package com.api.payments.messages;

public interface PaymentMessages {
        //Success Messages
    String noPaymentDaraRegistered = "Nenhum dado de pagamento cadastrado. ";
    String paymentDataNotFound = "Não foram encontrados os dados do pagamento. ";
    String paymentDataInserted = "Dados do pagamento inseridos com sucesso! ";
    String paymentDataUpdated = "Dados do pagamento atualizados com sucesso! ";
    String paymentDataDeleted = "Dados do pagamento excluídos com sucesso! ";
        //Error Messages
    String paymentDataNotInserted = "Não foi possível inserir os dados do pagamento. ";
    String paymentDataNotUpdated = "Não foi possível atualizar os dados do pagamento. ";
    String paymentDataNotDeleted = "Não foi possível excluir os dados do pagamento. ";
    String badRequest = "Não foi possível realizar esta operação. ";

}
