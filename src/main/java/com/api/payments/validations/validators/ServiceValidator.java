package com.api.payments.validations.validators;

import com.api.payments.dto.ServicesDto;
import org.hibernate.service.spi.ServiceException;

import static com.api.payments.validations.messages.ServiceValidatorMessages.*;

public class ServiceValidator {

    public static void serviceValidator(ServicesDto servicesData)
            throws ServiceException {

        String itemName = servicesData.getItemName();
        String itemCategory = servicesData.getItemCategory();
        double totalPrice = servicesData.getTotalPrice();
//        double discountPrice = servicesData.getDiscountPrice();
        String internalCode = servicesData.getInternalCode();
        String itemDescription = servicesData.getItemDescription();

        if(itemName == null) throw new ServiceException (itemNameInvalid);
        if(itemCategory == null) throw new ServiceException (categoryInvalid);
        if(totalPrice == 0) throw new ServiceException (totalPriceInvalid);
//        if(discountPrice == 0) throw new ServiceException (discountPriceInvalid);
        if(internalCode == null) throw new ServiceException (internalCodeInvalid);
        if(itemDescription == null) throw new ServiceException (descriptionInvalid);
    }
}
