package com.api.payments.validations.validators;

import org.hibernate.service.spi.ServiceException;

import java.util.Objects;

public class ValidateField {

    public static void validateField(Object field, String message) {
        if (Objects.isNull(field)) throw new ServiceException(message);

        if(field instanceof String) {
            if (((String) field).isBlank()) throw new ServiceException(message);
        }

        if (field instanceof Double) {
            if ((Double) field <= 0) throw new ServiceException(message);
        }
    }
}
