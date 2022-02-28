package com.api.payments.validations;

import java.time.LocalDate;

public class PaymentValidator {

    public static void paymentValidator(
            String debtorFullName,
            String debtorLastName,
            String paymentMethod,
            Boolean paymentStatus,
            LocalDate expirationDate,
            LocalDate paymentDate,
            String currency,
            double interest,
            double fine,
            double increasedValue,
            double discPayAdvance,
            double originalValue,
            double total,
            String description,
            String messageText
    )
            throws Exception {

        if (debtorFullName == null) throw new Exception (PaymentValidatorMessages.debtorFullNameInvalid);
        if (debtorLastName == null) throw new Exception (PaymentValidatorMessages.debtorLastNameInvalid);
        if (paymentMethod == null) throw new Exception (PaymentValidatorMessages.paymentMethodInvalid);
        if (paymentStatus == null) throw new Exception (PaymentValidatorMessages.paymentStatusInvalid);
        if (paymentDate == null) throw new Exception (PaymentValidatorMessages.paymentDateInvalid);
        if (expirationDate == null) throw new Exception (PaymentValidatorMessages.expirationDateInvalid);
        if (currency == null) throw new Exception (PaymentValidatorMessages.currencyInvalid);
//        if (interest == 0) throw new Exception (PaymentValidatorMessages.interestInvalid);
//        if (fine == 0) throw new Exception (PaymentValidatorMessages.fineInvalid);
//        if (increasedValue == 0) throw new Exception (PaymentValidatorMessages.increasedValueInvalid);
//        if (discPayAdvance == 0) throw new Exception (PaymentValidatorMessages.discPayAdvanceInvalid);
//        if (originalValue == 0) throw new Exception (PaymentValidatorMessages.originalValueInvalid);
//        if (total == 0) throw new Exception (PaymentValidatorMessages.totalInvalid);
        if (description == null) throw new Exception (PaymentValidatorMessages.descriptionInvalid);
        if (messageText == null) throw new Exception (PaymentValidatorMessages.messageTextInvalid);
    }
}
