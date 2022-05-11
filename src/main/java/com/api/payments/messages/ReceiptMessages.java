package com.api.payments.messages;

public interface ReceiptMessages {
        //Success Messages
    String receiptsEmpty = "Nenhum recebimento cadastrado. ";
    String receiptNotFound = "Recebimento não encontrado. ";
    String receiptCreated = "Recebimento cadastrado com sucesso! ";
    String receiptDataUpdated = "Dados do recebimento atualizados com sucesso! ";
    String receiptDataDeleted = "Dados do recebimento deletados com sucesso! ";
        //Error Messages
    String receiptNotCreated = "Não foi possível cadastrar o recebimento. ";
    String receiptDataNotUpdated = "Não foi possível atualizar os dados do recebimento. ";
    String receiptDataNotDeleted = "Não foi possível deletar os dados do recebimento. ";

}
