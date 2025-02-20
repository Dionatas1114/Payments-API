package com.api.payments.validations.validators;

import com.api.payments.dto.ProductsDto;
import org.hibernate.service.spi.ServiceException;

import static com.api.payments.validations.validators.ValidateField.validateField;
import static com.api.payments.validations.messages.ProductValidatorMessages.*;

public class ProductValidator {

    public static void productValidator(ProductsDto productsData)
            throws ServiceException {

        validateField(productsData.getItemName(), itemNameInvalid);
        validateField(productsData.getProductBrand(), productBrandInvalid);
        validateField(productsData.getItemCategory(), categoryInvalid);
        validateField(productsData.getManufacturer(), manufacturerInvalid);
        validateField(productsData.getCaptionPacking(), captionPackingInvalid);
        validateField(productsData.getBarCode(), barCodeInvalid);
        validateField(productsData.getInternalCode(), internalCodeInvalid);
//        validateField(productsData.getItemDescription(), descriptionInvalid);

        validateField(productsData.getTotalPrice(), totalPriceInvalid);
        validateField(productsData.getUnitaryPrice(), unitaryPriceInvalid);
        validateField(productsData.getDiscountPrice(), discountPriceInvalid);
    }
}
