package com.api.payments.validations;

import com.api.payments.dto.ReceiptsDto;
import org.hibernate.service.spi.ServiceException;

import java.time.LocalDate;

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
