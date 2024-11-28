package com.api.payments.validations.validators;

import com.api.payments.dto.TransactionDto;
import com.api.payments.enums.PaymentMethods;
import org.hibernate.service.spi.ServiceException;

import static com.api.payments.validations.validators.ValidateField.validateField;
import static com.api.payments.validations.messages.PaymentValidatorMessages.*;

public class PaymentValidator {

    public static void paymentValidator(TransactionDto paymentsData) throws ServiceException {

        paymentMethodValidator(paymentsData.getPaymentMethod());

        validateField(paymentsData.getDebtorFullName(), debtorFullNameInvalid);
        validateField(paymentsData.getDebtorLastName(), debtorLastNameInvalid);
        validateField(paymentsData.getUser(), userIdInvalid);
        validateField(paymentsData.getUser().getId(), userIdInvalid);

        validateField(paymentsData.getPaymentStatus(), paymentStatusInvalid); //TODO create enum
        validateField(paymentsData.getCurrency(), currencyInvalid); //TODO create enum
        validateField(paymentsData.getPaymentDate(), paymentDateInvalid);
        validateField(paymentsData.getExpirationDate(), expirationDateInvalid);

//        validateField(paymentsData.getInterest(), interestInvalid);
//        validateField(paymentsData.getFine(), fineInvalid);
//        validateField(paymentsData.getIncreasedValue(), increasedValueInvalid);
//        validateField(paymentsData.getDiscPayAdvance(), discPayAdvanceInvalid);
//        validateField(paymentsData.getOriginalValue(), originalValueInvalid);
//        validateField(paymentsData.getTotal(), totalInvalid);

        validateField(paymentsData.getDescription(), descriptionInvalid);
        validateField(paymentsData.getMessageText(), messageTextInvalid);
    }

    private static void paymentMethodValidator(String paymentMethod) {
        validateField(paymentMethod, paymentMethodInvalid);
        PaymentMethods.fromValue(paymentMethod);
    }
}
