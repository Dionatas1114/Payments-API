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
            throws ExceptionInInitializerError {

        if (debtorFullName == null) throw new ExceptionInInitializerError (PaymentValidatorMessages.debtorFullNameInvalid);
        if (debtorLastName == null) throw new ExceptionInInitializerError (PaymentValidatorMessages.debtorLastNameInvalid);
        if (paymentMethod == null) throw new ExceptionInInitializerError (PaymentValidatorMessages.paymentMethodInvalid);
        if (paymentStatus == null) throw new ExceptionInInitializerError (PaymentValidatorMessages.paymentStatusInvalid);
        if (paymentDate == null) throw new ExceptionInInitializerError (PaymentValidatorMessages.paymentDateInvalid);
        if (expirationDate == null) throw new ExceptionInInitializerError (PaymentValidatorMessages.expirationDateInvalid);
        if (currency == null) throw new ExceptionInInitializerError (PaymentValidatorMessages.currencyInvalid);
//        if (interest == 0) throw new ExceptionInInitializerError (PaymentValidatorMessages.interestInvalid);
//        if (fine == 0) throw new ExceptionInInitializerError (PaymentValidatorMessages.fineInvalid);
//        if (increasedValue == 0) throw new ExceptionInInitializerError (PaymentValidatorMessages.increasedValueInvalid);
//        if (discPayAdvance == 0) throw new ExceptionInInitializerError (PaymentValidatorMessages.discPayAdvanceInvalid);
//        if (originalValue == 0) throw new ExceptionInInitializerError (PaymentValidatorMessages.originalValueInvalid);
//        if (total == 0) throw new ExceptionInInitializerError (PaymentValidatorMessages.totalInvalid);
        if (description == null) throw new ExceptionInInitializerError (PaymentValidatorMessages.descriptionInvalid);
        if (messageText == null) throw new ExceptionInInitializerError (PaymentValidatorMessages.messageTextInvalid);
    }
}
