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
            throws Exception {

        if (debtorFullName == null) throw new Exception (ReceiptValidatorMessages.debtorFullNameInvalid);
        if (debtorLastName == null) throw new Exception (ReceiptValidatorMessages.debtorLastNameInvalid);
        if (paymentMethod == null) throw new Exception (ReceiptValidatorMessages.paymentMethodInvalid);
        if (paymentStatus == null) throw new Exception (ReceiptValidatorMessages.paymentStatusInvalid);
        if (paymentDate == null) throw new Exception (ReceiptValidatorMessages.paymentDateInvalid);
        if (expirationDate == null) throw new Exception (ReceiptValidatorMessages.expirationDateInvalid);
        if (currency == null) throw new Exception (ReceiptValidatorMessages.currencyInvalid);
//        if (interest == 0) throw new Exception (ReceiptValidatorMessages.interestInvalid);
//        if (fine == 0) throw new Exception (ReceiptValidatorMessages.fineInvalid);
//        if (increasedValue == 0) throw new Exception (ReceiptValidatorMessages.increasedValueInvalid);
//        if (discPayAdvance == 0) throw new Exception (ReceiptValidatorMessages.discPayAdvanceInvalid);
//        if (originalValue == 0) throw new Exception (ReceiptValidatorMessages.originalValueInvalid);
//        if (total == 0) throw new Exception (ReceiptValidatorMessages.totalInvalid);
        if (description == null) throw new Exception (ReceiptValidatorMessages.descriptionInvalid);
        if (messageText == null) throw new Exception (ReceiptValidatorMessages.messageTextInvalid);
    }
}
