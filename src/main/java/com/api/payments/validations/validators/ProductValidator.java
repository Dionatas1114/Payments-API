package com.api.payments.validations.validators;

import com.api.payments.dto.ProductsDto;
import org.hibernate.service.spi.ServiceException;

import static com.api.payments.validations.messages.ProductValidatorMessages.*;

public class ProductValidator {

    public static void productValidator(ProductsDto productsData)
            throws ServiceException {

        String itemName = productsData.getItemName ();
        String productBrand = productsData.getProductBrand ();
        String category = productsData.getItemCategory();
        String manufacturer = productsData.getManufacturer ();
        String captionPacking = productsData.getCaptionPacking ();
        double totalPrice = productsData.getTotalPrice ();
        double unitaryPrice = productsData.getUnitaryPrice ();
        double discountPrice = productsData.getDiscountPrice ();
        String barCode = productsData.getBarCode ();
        String internalCode = productsData.getInternalCode ();
//        String description = productsData.getItemDescription ();

        itemNameValidator(itemName);
        if (productBrand == null) throw new ServiceException (productBrandInvalid);
        if (category == null) throw new ServiceException (categoryInvalid);
        if (manufacturer == null) throw new ServiceException (manufacturerInvalid);
        if (captionPacking == null) throw new ServiceException (captionPackingInvalid);
        if (totalPrice == 0) throw new ServiceException (totalPriceInvalid);
        if (unitaryPrice == 0) throw new ServiceException (unitaryPriceInvalid);
        if (discountPrice == 0) throw new ServiceException (discountPriceInvalid);
        if (barCode == null) throw new ServiceException (barCodeInvalid);
        if (internalCode == null) throw new ServiceException (internalCodeInvalid);
//        if (description == null) throw new ServiceException (descriptionInvalid);
    }

    public static void itemNameValidator(String itemName) {
        if (itemName == null) throw new ServiceException(itemNameInvalid);
    }
}
