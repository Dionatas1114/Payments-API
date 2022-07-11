package com.api.payments.validations.validators;

import com.api.payments.dto.ItemsDto;
import com.api.payments.validations.messages.ItemValidatorMessages;
import org.hibernate.service.spi.ServiceException;

public class ItemValidator {

    public static void itemValidator(ItemsDto itemsData)
            throws ServiceException {

        String itemName = itemsData.getItemName ();
        String itemType = itemsData.getItemType ();
        String productBrand = itemsData.getProductBrand ();
        String category = itemsData.getCategory ();
        String manufacturer = itemsData.getManufacturer ();
        String captionPacking = itemsData.getCaptionPacking ();
        double totalPrice = itemsData.getTotalPrice ();
        double unitaryPrice = itemsData.getUnitaryPrice ();
        double discountPrice = itemsData.getDiscountPrice ();
        String barCode = itemsData.getBarCode ();
        String internalCode = itemsData.getInternalCode ();
        String description = itemsData.getDescription ();

        itemNameValidator(itemName);
        itemTypeValidator(itemType);
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

    public static void itemTypeValidator(String itemType) {
        if (itemType == null) throw new ServiceException (ItemValidatorMessages.itemTypeInvalid);
    }

    public static void itemNameValidator(String itemName) {
        if (itemName == null) throw new ServiceException(ItemValidatorMessages.itemNameInvalid);
    }
}
