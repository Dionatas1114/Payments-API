package com.api.payments.validations;

import org.hibernate.service.spi.ServiceException;

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
            throws ServiceException {

        if (debtorFullName == null) throw new ServiceException (PaymentValidatorMessages.debtorFullNameInvalid);
        if (debtorLastName == null) throw new ServiceException (PaymentValidatorMessages.debtorLastNameInvalid);
        if (paymentMethod == null) throw new ServiceException (PaymentValidatorMessages.paymentMethodInvalid);
        if (paymentStatus == null) throw new ServiceException (PaymentValidatorMessages.paymentStatusInvalid);
        if (paymentDate == null) throw new ServiceException (PaymentValidatorMessages.paymentDateInvalid);
        if (expirationDate == null) throw new ServiceException (PaymentValidatorMessages.expirationDateInvalid);
        if (currency == null) throw new ServiceException(PaymentValidatorMessages.currencyInvalid);
//        if (interest == 0) throw new ServiceException (PaymentValidatorMessages.interestInvalid);
//        if (fine == 0) throw new ServiceException (PaymentValidatorMessages.fineInvalid);
//        if (increasedValue == 0) throw new ServiceException (PaymentValidatorMessages.increasedValueInvalid);
//        if (discPayAdvance == 0) throw new ServiceException (PaymentValidatorMessages.discPayAdvanceInvalid);
//        if (originalValue == 0) throw new ServiceException (PaymentValidatorMessages.originalValueInvalid);
//        if (total == 0) throw new ServiceException (PaymentValidatorMessages.totalInvalid);
        if (description == null) throw new ServiceException (PaymentValidatorMessages.descriptionInvalid);
        if (messageText == null) throw new ServiceException (PaymentValidatorMessages.messageTextInvalid);
    }
}
