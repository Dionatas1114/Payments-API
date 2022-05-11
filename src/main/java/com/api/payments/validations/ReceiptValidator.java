package com.api.payments.validations;

import java.time.LocalDate;

public class ReceiptValidator {

    public static void receiptValidator(
            String debtorFullName,
            String debtorLastName,
            String paymentMethod,
            Boolean paymentStatus,
            LocalDate paymentDate,
            LocalDate expirationDate,
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

        if (debtorFullName == null) throw new ExceptionInInitializerError (ReceiptValidatorMessages.debtorFullNameInvalid);
        if (debtorLastName == null) throw new ExceptionInInitializerError (ReceiptValidatorMessages.debtorLastNameInvalid);
        if (paymentMethod == null) throw new ExceptionInInitializerError (ReceiptValidatorMessages.paymentMethodInvalid);
        if (paymentStatus == null) throw new ExceptionInInitializerError (ReceiptValidatorMessages.paymentStatusInvalid);
        if (paymentDate == null) throw new ExceptionInInitializerError (ReceiptValidatorMessages.paymentDateInvalid);
        if (expirationDate == null) throw new ExceptionInInitializerError (ReceiptValidatorMessages.expirationDateInvalid);
        if (currency == null) throw new ExceptionInInitializerError (ReceiptValidatorMessages.currencyInvalid);
//        if (interest == 0) throw new ExceptionInInitializerError (ReceiptValidatorMessages.interestInvalid);
//        if (fine == 0) throw new ExceptionInInitializerError (ReceiptValidatorMessages.fineInvalid);
//        if (increasedValue == 0) throw new ExceptionInInitializerError (ReceiptValidatorMessages.increasedValueInvalid);
//        if (discPayAdvance == 0) throw new ExceptionInInitializerError (ReceiptValidatorMessages.discPayAdvanceInvalid);
//        if (originalValue == 0) throw new ExceptionInInitializerError (ReceiptValidatorMessages.originalValueInvalid);
//        if (total == 0) throw new ExceptionInInitializerError (ReceiptValidatorMessages.totalInvalid);
        if (description == null) throw new ExceptionInInitializerError (ReceiptValidatorMessages.descriptionInvalid);
        if (messageText == null) throw new ExceptionInInitializerError (ReceiptValidatorMessages.messageTextInvalid);
    }
}
