package com.api.payments.services;

import com.api.payments.model.ItemModel;
import com.api.payments.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.api.payments.validations.ItemValidator.itemValidator;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    public void saveItemData(ItemModel itemData) throws Exception {

        String itemName = itemData.getItemName ();
        String itemType = itemData.getItemType ();
        String productBrand = itemData.getProductBrand ();
        String category = itemData.getCategory ();
        String manufacturer = itemData.getManufacturer ();
        String captionPacking = itemData.getCaptionPacking ();
        double totalPrice = itemData.getTotalPrice ();
        double unitaryPrice = itemData.getUnitaryPrice ();
        double discountPrice = itemData.getDiscountPrice ();
        String barCode = itemData.getBarCode ();
        String internalCode = itemData.getInternalCode ();
        String description = itemData.getDescription ();

        itemValidator (
                itemName,
                itemType,
                productBrand,
                category,
                manufacturer,
                captionPacking,
                totalPrice,
                unitaryPrice,
                discountPrice,
                barCode,
                internalCode,
                description
        );

        itemRepository.save (itemData);
    }
}
