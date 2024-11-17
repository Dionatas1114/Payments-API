package com.api.payments.enums;

import lombok.Getter;
import org.hibernate.service.spi.ServiceException;

import static com.api.payments.validations.messages.PaymentValidatorMessages.paymentMethodInvalid;

@Getter
public enum PaymentMethods {
    CASH("cash"),
    CREDIT_CARD("credit"),
    DEBIT_CARD("debit"),
    PIX("pix"),
    PIX_QR_CODE("pix_qr_code"),
    OTHER("other");

    private final String value;

    PaymentMethods(String value) {
        this.value = value;
    }

    public static void fromValue(String value) {
        for (PaymentMethods method : PaymentMethods.values()) {
            if (method.getValue().equalsIgnoreCase(value)) {
                return;
            }
        }
        throw new ServiceException(paymentMethodInvalid);
    }
}
