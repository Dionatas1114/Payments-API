package com.api.payments.services;

import com.api.payments.entity.Items;
import com.api.payments.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.api.payments.validations.ItemValidator.itemValidator;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    public void saveItemData(Items itemsData) throws Exception {

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

        itemRepository.save (itemsData);
    }
}
