package com.api.payments.validations.validators;

import com.api.payments.dto.TransactionDto;
import org.hibernate.service.spi.ServiceException;

import static com.api.payments.utils.ValidateField.validateField;
import static com.api.payments.validations.messages.ReceiptValidatorMessages.*;

public class ReceiptValidator {

    public static void receiptValidator(TransactionDto receiptsData)
            throws ServiceException {

        validateField(receiptsData.getDebtorFullName(), debtorFullNameInvalid);
        validateField(receiptsData.getDebtorLastName(), debtorLastNameInvalid);
        validateField(receiptsData.getPaymentMethod(), paymentMethodInvalid);
        validateField(receiptsData.getPaymentStatus(), paymentStatusInvalid);
        validateField(receiptsData.getPaymentDate(), paymentDateInvalid);
        validateField(receiptsData.getExpirationDate(), expirationDateInvalid);
        validateField(receiptsData.getCurrency(), currencyInvalid);
//        validateField(receiptsData.getInterest(), interestInvalid);
//        validateField(receiptsData.getFine(), fineInvalid);
//        validateField(receiptsData.getIncreasedValue(), increasedValueInvalid);
//        validateField(receiptsData.getDiscPayAdvance(), discPayAdvanceInvalid);
//        validateField(receiptsData.getOriginalValue(), originalValueInvalid);
//        validateField(receiptsData.getTotal(), totalInvalid);
        validateField(receiptsData.getDescription(), descriptionInvalid);
        validateField(receiptsData.getMessageText(), messageTextInvalid);
    }
}
