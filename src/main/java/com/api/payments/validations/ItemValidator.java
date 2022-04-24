package com.api.payments.validations;

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
            throws ExceptionInInitializerError {

        if (itemName == null) throw new ExceptionInInitializerError (ItemValidatorMessages.itemNameInvalid);
        if (itemType == null) throw new ExceptionInInitializerError (ItemValidatorMessages.itemTypeInvalid);
        if (productBrand == null) throw new ExceptionInInitializerError (ItemValidatorMessages.productBrandInvalid);
        if (category == null) throw new ExceptionInInitializerError (ItemValidatorMessages.categoryInvalid);
        if (manufacturer == null) throw new ExceptionInInitializerError (ItemValidatorMessages.manufacturerInvalid);
        if (captionPacking == null) throw new ExceptionInInitializerError (ItemValidatorMessages.captionPackingInvalid);
        if (totalPrice == 0) throw new ExceptionInInitializerError (ItemValidatorMessages.totalPriceInvalid);
        if (unitaryPrice == 0) throw new ExceptionInInitializerError (ItemValidatorMessages.unitaryPriceInvalid);
        if (discountPrice == 0) throw new ExceptionInInitializerError (ItemValidatorMessages.discountPriceInvalid);
        if (barCode == null) throw new ExceptionInInitializerError (ItemValidatorMessages.barCodeInvalid);
        if (internalCode == null) throw new ExceptionInInitializerError (ItemValidatorMessages.internalCodeInvalid);
        if (description == null) throw new ExceptionInInitializerError (ItemValidatorMessages.descriptionInvalid);
    }
}
