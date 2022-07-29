package com.api.payments.validations.validators;

import com.api.payments.dto.TransactionDto;
import org.hibernate.service.spi.ServiceException;

import java.time.LocalDate;
import java.util.UUID;

import static com.api.payments.validations.messages.PaymentValidatorMessages.*;

public class PaymentValidator {

    public static void paymentValidator(TransactionDto paymentsData)
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

        if (debtorFullName == null) throw new ServiceException (debtorFullNameInvalid);
        if (debtorLastName == null) throw new ServiceException (debtorLastNameInvalid);
        if (paymentMethod == null) throw new ServiceException (paymentMethodInvalid);
        if (paymentStatus == null) throw new ServiceException (paymentStatusInvalid);
        if (paymentDate == null) throw new ServiceException (paymentDateInvalid);
        if (expirationDate == null) throw new ServiceException (expirationDateInvalid);
        if (currency == null) throw new ServiceException(currencyInvalid);
//        if (interest == 0) throw new ServiceException (interestInvalid);
//        if (fine == 0) throw new ServiceException (fineInvalid);
//        if (increasedValue == 0) throw new ServiceException (increasedValueInvalid);
//        if (discPayAdvance == 0) throw new ServiceException (discPayAdvanceInvalid);
//        if (originalValue == 0) throw new ServiceException (originalValueInvalid);
//        if (total == 0) throw new ServiceException (totalInvalid);
        if (description == null) throw new ServiceException (descriptionInvalid);
        if (messageText == null) throw new ServiceException (messageTextInvalid);
        if (userId == null) throw new ServiceException (userIdInvalid);
    }
}
