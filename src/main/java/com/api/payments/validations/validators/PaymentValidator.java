package com.api.payments.validations.validators;

import com.api.payments.dto.TransactionDto;
import org.hibernate.service.spi.ServiceException;

import static com.api.payments.validations.messages.PaymentValidatorMessages.currencyInvalid;
import static com.api.payments.validations.messages.PaymentValidatorMessages.debtorFullNameInvalid;
import static com.api.payments.validations.messages.PaymentValidatorMessages.debtorLastNameInvalid;
import static com.api.payments.validations.messages.PaymentValidatorMessages.descriptionInvalid;
import static com.api.payments.validations.messages.PaymentValidatorMessages.expirationDateInvalid;
import static com.api.payments.validations.messages.PaymentValidatorMessages.messageTextInvalid;
import static com.api.payments.validations.messages.PaymentValidatorMessages.paymentDateInvalid;
import static com.api.payments.validations.messages.PaymentValidatorMessages.paymentMethodInvalid;
import static com.api.payments.validations.messages.PaymentValidatorMessages.paymentStatusInvalid;
import static com.api.payments.validations.messages.PaymentValidatorMessages.userIdInvalid;
import static com.api.payments.validations.validators.ValidateField.validateField;

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
    }
}
