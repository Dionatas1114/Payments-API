package com.api.payments.validations.validators;

import com.api.payments.dto.ServicesDto;
import org.hibernate.service.spi.ServiceException;

import static com.api.payments.utils.ValidateField.validateField;
import static com.api.payments.validations.messages.ServiceValidatorMessages.*;

public class ServiceValidator {

    public static void serviceValidator(ServicesDto servicesData)
            throws ServiceException {

        validateField(servicesData.getItemName(), itemNameInvalid);
        validateField(servicesData.getItemCategory(), categoryInvalid);
        validateField(servicesData.getInternalCode(), internalCodeInvalid);
//        validateField(servicesData.getItemDescription(), descriptionInvalid);

        validateField(servicesData.getTotalPrice(), totalPriceInvalid);
//        validateField(servicesData.getDiscountPrice(), discountPriceInvalid);
    }
}
