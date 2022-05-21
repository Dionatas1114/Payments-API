package com.api.payments.validations;

import org.hibernate.service.spi.ServiceException;

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
            throws ServiceException {

        if (debtorFullName == null) throw new ServiceException (ReceiptValidatorMessages.debtorFullNameInvalid);
        if (debtorLastName == null) throw new ServiceException (ReceiptValidatorMessages.debtorLastNameInvalid);
        if (paymentMethod == null) throw new ServiceException(ReceiptValidatorMessages.paymentMethodInvalid);
        if (paymentStatus == null) throw new ServiceException (ReceiptValidatorMessages.paymentStatusInvalid);
        if (paymentDate == null) throw new ServiceException (ReceiptValidatorMessages.paymentDateInvalid);
        if (expirationDate == null) throw new ServiceException (ReceiptValidatorMessages.expirationDateInvalid);
        if (currency == null) throw new ServiceException (ReceiptValidatorMessages.currencyInvalid);
//        if (interest == 0) throw new ServiceException (ReceiptValidatorMessages.interestInvalid);
//        if (fine == 0) throw new ServiceException (ReceiptValidatorMessages.fineInvalid);
//        if (increasedValue == 0) throw new ServiceException (ReceiptValidatorMessages.increasedValueInvalid);
//        if (discPayAdvance == 0) throw new ServiceException (ReceiptValidatorMessages.discPayAdvanceInvalid);
//        if (originalValue == 0) throw new ServiceException (ReceiptValidatorMessages.originalValueInvalid);
//        if (total == 0) throw new ServiceException (ReceiptValidatorMessages.totalInvalid);
        if (description == null) throw new ServiceException (ReceiptValidatorMessages.descriptionInvalid);
        if (messageText == null) throw new ServiceException (ReceiptValidatorMessages.messageTextInvalid);
    }
}
