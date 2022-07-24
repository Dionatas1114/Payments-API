package com.api.payments.validations.validators;

import com.api.payments.dto.ReceiptsDto;
import org.hibernate.service.spi.ServiceException;

import java.time.LocalDate;

import static com.api.payments.validations.messages.ReceiptValidatorMessages.*;

public class ReceiptValidator {

    public static void receiptValidator(ReceiptsDto receiptsData)
            throws ServiceException {

        String debtorFullName = receiptsData.getDebtorFullName ();
        String debtorLastName = receiptsData.getDebtorLastName ();
        String paymentMethod = receiptsData.getPaymentMethod ();
        Boolean paymentStatus = receiptsData.getPaymentStatus ();
        LocalDate paymentDate = receiptsData.getPaymentDate ();
        LocalDate expirationDate = receiptsData.getExpirationDate ();
        String currency = receiptsData.getCurrency ();
        double interest = receiptsData.getInterest ();
        double fine = receiptsData.getFine ();
        double increasedValue = receiptsData.getIncreasedValue ();
        double discPayAdvance = receiptsData.getDiscPayAdvance ();
        double originalValue = receiptsData.getOriginalValue ();
        double total = receiptsData.getTotal ();
        String description = receiptsData.getDescription ();
        String messageText = receiptsData.getMessageText ();

        if (debtorFullName == null) throw new ServiceException (debtorFullNameInvalid);
        if (debtorLastName == null) throw new ServiceException (debtorLastNameInvalid);
        if (paymentMethod == null) throw new ServiceException(paymentMethodInvalid);
        if (paymentStatus == null) throw new ServiceException (paymentStatusInvalid);
        if (paymentDate == null) throw new ServiceException (paymentDateInvalid);
        if (expirationDate == null) throw new ServiceException (expirationDateInvalid);
        if (currency == null) throw new ServiceException (currencyInvalid);
//        if (interest == 0) throw new ServiceException (interestInvalid);
//        if (fine == 0) throw new ServiceException (fineInvalid);
//        if (increasedValue == 0) throw new ServiceException (increasedValueInvalid);
//        if (discPayAdvance == 0) throw new ServiceException (discPayAdvanceInvalid);
//        if (originalValue == 0) throw new ServiceException (originalValueInvalid);
//        if (total == 0) throw new ServiceException (totalInvalid);
        if (description == null) throw new ServiceException (descriptionInvalid);
        if (messageText == null) throw new ServiceException (messageTextInvalid);
    }
}
