package com.api.payments.messages;

public interface PaymentMessages {
        //Success Messages
    String paymentsEmpty = "Nenhum pagamento cadastrado";
    String paymentNotFound = "Pagamento não encontrado";
    String paymentCreated = "Pagamento cadastrado com sucesso!";
    String paymentDataUpdated = "Dados do pagamento atualizados com sucesso!";
    String paymentDataDeleted = "Dados do pagamento deletados com sucesso!";
        //Error Messages
    String paymentNotCreated = "Não foi possível cadastrar o pagamento, tente mais tarde";
    String paymentDataNotUpdated = "Não foi possível atualizar os dados do pagamento, tente mais tarde";
    String paymentDataNotDeleted = "Não foi possível deletar os dados do pagamento, tente mais tarde";

}
