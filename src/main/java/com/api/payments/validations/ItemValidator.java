package com.api.payments.validations;

import org.hibernate.service.spi.ServiceException;

public class ItemValidator {

    public static void itemValidator(
            String itemName,
            String itemType,
            String productBrand,
            String category,
            String manufacturer,
            String captionPacking,
            double totalPrice,
            double unitaryPrice,
            double discountPrice,
            String barCode,
            String internalCode,
            String description
    )
            throws ServiceException {

        if (itemName == null) throw new ServiceException(ItemValidatorMessages.itemNameInvalid);
        if (itemType == null) throw new ServiceException (ItemValidatorMessages.itemTypeInvalid);
        if (productBrand == null) throw new ServiceException (ItemValidatorMessages.productBrandInvalid);
        if (category == null) throw new ServiceException (ItemValidatorMessages.categoryInvalid);
        if (manufacturer == null) throw new ServiceException (ItemValidatorMessages.manufacturerInvalid);
        if (captionPacking == null) throw new ServiceException (ItemValidatorMessages.captionPackingInvalid);
        if (totalPrice == 0) throw new ServiceException (ItemValidatorMessages.totalPriceInvalid);
        if (unitaryPrice == 0) throw new ServiceException (ItemValidatorMessages.unitaryPriceInvalid);
        if (discountPrice == 0) throw new ServiceException (ItemValidatorMessages.discountPriceInvalid);
        if (barCode == null) throw new ServiceException (ItemValidatorMessages.barCodeInvalid);
        if (internalCode == null) throw new ServiceException (ItemValidatorMessages.internalCodeInvalid);
        if (description == null) throw new ServiceException (ItemValidatorMessages.descriptionInvalid);
    }
}
