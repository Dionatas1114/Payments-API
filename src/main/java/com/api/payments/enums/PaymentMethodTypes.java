package com.api.payments.enums;

import lombok.Getter;

@Getter
public enum PaymentMethodTypes {
    CASH("cash"),
    CREDIT_CARD("credit"),
    DEBIT_CARD("debit"),
    PIX("pix"),
    PIX_QR_CODE("pix_qr_code"),
    OTHER("other");

    private final String value;

    PaymentMethodTypes(String value) {
        this.value = value;
    }
}
