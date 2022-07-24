package com.api.payments.messages;

public interface ReceiptMessages {
        //Success Messages
    String noReceiptDataRegistered = "Nenhum dado de recebimento cadastrado. ";
    String receiptDataNotFound = "Não foram encontrados os dados do recebimento. ";
    String receiptDataInserted = "Dados do recebimento inseridos com sucesso! ";
    String receiptDataUpdated = "Dados do recebimento atualizados com sucesso! ";
    String receiptDataDeleted = "Dados do recebimento excluídos com sucesso! ";
        //Error Messages
    String receiptDataNotInserted = "Não foi possível inserir os dados do recebimento. ";
    String receiptDataNotUpdated = "Não foi possível atualizar os dados do recebimento. ";
    String receiptDataNotDeleted = "Não foi possível excluir os dados do recebimento. ";
    String badRequest = "Não foi possível realizar esta operação. ";

}
