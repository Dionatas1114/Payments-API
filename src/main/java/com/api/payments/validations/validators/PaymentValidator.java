package com.api.payments.validations.validators;

import com.api.payments.dto.PaymentsDto;
import com.api.payments.validations.messages.PaymentValidatorMessages;
import org.hibernate.service.spi.ServiceException;

import java.time.LocalDate;
import java.util.UUID;

public class PaymentValidator {

    public static void paymentValidator(PaymentsDto paymentsData)
            throws ServiceException {

        String debtorFullName = paymentsData.getDebtorFullName ();
        String debtorLastName = paymentsData.getDebtorLastName ();
        String paymentMethod = paymentsData.getPaymentMethod ();
        Boolean paymentStatus = paymentsData.getPaymentStatus ();
        LocalDate paymentDate = paymentsData.getPaymentDate ();
        LocalDate expirationDate = paymentsData.getExpirationDate ();
        String currency = paymentsData.getCurrency ();
        double interest = paymentsData.getInterest ();
        double fine = paymentsData.getFine ();
        double increasedValue = paymentsData.getIncreasedValue ();
        double discPayAdvance = paymentsData.getDiscPayAdvance ();
        double originalValue = paymentsData.getOriginalValue ();
        double total = paymentsData.getTotal ();
        String description = paymentsData.getDescription ();
        String messageText = paymentsData.getMessageText ();
        UUID userId = paymentsData.getUser().getId();

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
        if (userId == null) throw new ServiceException (PaymentValidatorMessages.userIdInvalid);
    }
}
