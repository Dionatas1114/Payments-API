package com.api.payments.services.impl;

import com.api.payments.dto.ItemsDto;
import com.api.payments.entity.Items;
import com.api.payments.repository.ItemRepository;
import com.api.payments.services.ItemService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.sonatype.aether.RepositoryException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.api.payments.messages.ItemMessages.itemNotFound;
import static com.api.payments.messages.ItemMessages.itemsEmpty;
import static com.api.payments.validations.ItemValidator.itemValidator;

@Service
@AllArgsConstructor
public class ItemServiceImpl implements ItemService {

    private ItemRepository itemRepository;

    @Override
    public List<ItemsDto> findAllItems() throws Exception {

        List<Items> itemsList = itemRepository.findAll();
        List<ItemsDto> itemsDtoList = new ArrayList<>();

        boolean itemsListEmpty = itemsList.isEmpty();
        if (itemsListEmpty) throw new RepositoryException(itemsEmpty);

        for(Items items : itemsList) itemsDtoList.add(convertToDto(items));

        return itemsDtoList;
    }

    @Override
    public ItemsDto findOneItems(UUID itemId) throws RepositoryException {
        Optional<Items> itemFind = itemRepository.findById(itemId);
        if (itemFind.isEmpty()) throw new RepositoryException(itemNotFound);

        return convertToDto(itemFind.get());
    }

    public void saveItemData(ItemsDto itemsData) throws Exception {

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

        Items items = convertFromDto(itemsData);

        try {
            itemRepository.save (items);
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public void updateItemData(UUID itemId, ItemsDto itemsData) throws Exception {

        boolean existsById = itemRepository.existsById(itemId);
        if (!existsById) throw new RepositoryException(itemNotFound);

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

        Items items = convertFromDto(itemsData);

        try {
            items.setId(itemId);
            itemRepository.save (items);
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public void deleteItemData(UUID itemId) throws Exception {
        boolean existsById = itemRepository.existsById(itemId);
        if (!existsById) throw new RepositoryException(itemNotFound);

        try {
            itemRepository.deleteById(itemId);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

    }

    private ItemsDto convertToDto(Items items) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(items, ItemsDto.class);
    }

    private Items convertFromDto(ItemsDto itemsData) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(itemsData, Items.class);
    }
}
