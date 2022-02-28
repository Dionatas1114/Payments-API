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
            throws Exception {

        if (itemName == null) throw new Exception (ItemValidatorMessages.itemNameInvalid);
        if (itemType == null) throw new Exception (ItemValidatorMessages.itemTypeInvalid);
        if (productBrand == null) throw new Exception (ItemValidatorMessages.productBrandInvalid);
        if (category == null) throw new Exception (ItemValidatorMessages.categoryInvalid);
        if (manufacturer == null) throw new Exception (ItemValidatorMessages.manufacturerInvalid);
        if (captionPacking == null) throw new Exception (ItemValidatorMessages.captionPackingInvalid);
        if (totalPrice == 0) throw new Exception (ItemValidatorMessages.totalPriceInvalid);
        if (unitaryPrice == 0) throw new Exception (ItemValidatorMessages.unitaryPriceInvalid);
        if (discountPrice == 0) throw new Exception (ItemValidatorMessages.discountPriceInvalid);
        if (barCode == null) throw new Exception (ItemValidatorMessages.barCodeInvalid);
        if (internalCode == null) throw new Exception (ItemValidatorMessages.internalCodeInvalid);
        if (description == null) throw new Exception (ItemValidatorMessages.descriptionInvalid);
    }
}
