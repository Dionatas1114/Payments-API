package com.api.payments.validations.messages;

public interface PaymentValidatorMessages {

    String debtorFullNameInvalid = "Nome do completo do devedor está ausente ou é inválido";
    String debtorLastNameInvalid = "Sobrenome do devedor está ausente ou é inválido";
    String paymentMethodInvalid = "Método de pagamento está ausente ou é inválido";
    String paymentStatusInvalid = "Status de pagamento está ausente ou é inválido";
    String paymentDateInvalid = "Data de pagamento está ausente ou é inválido";
    String expirationDateInvalid = "Data limite de pagamento está ausente ou é inválido";
    String currencyInvalid = "Moeda está ausente ou é inválido";
    String interestInvalid = "Juros está ausente ou é inválido";
    String fineInvalid = "Multa está ausente ou é inválido";
    String increasedValueInvalid = "Valor acrescentado está ausente ou é inválido";
    String discPayAdvanceInvalid = "Disconto por pagamento antecipado está ausente ou é inválido";
    String originalValueInvalid = "Valor original está ausente ou é inválido";
    String totalInvalid = "Valor total está ausente ou é inválido";
    String descriptionInvalid = "Descrição está ausente ou é inválido";
    String messageTextInvalid = "Mensagem está ausente ou é inválido";

}
