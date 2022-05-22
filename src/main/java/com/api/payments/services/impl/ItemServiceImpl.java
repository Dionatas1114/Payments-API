package com.api.payments.services.impl;

import com.api.payments.dto.ItemsDto;
import com.api.payments.entity.Items;
import com.api.payments.repository.ItemRepository;
import com.api.payments.services.ItemService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.sonatype.aether.RepositoryException;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.api.payments.messages.ItemMessages.*;
import static com.api.payments.validations.ItemValidator.itemValidator;

@Service
@AllArgsConstructor
public class ItemServiceImpl implements ItemService {

    private ItemRepository itemRepository;

    @Override
    public List<ItemsDto> findAllItems() throws Exception {

        List<Items> itemsList = itemRepository.findAll();
        List<ItemsDto> itemsDtoList = new ArrayList<>();

        if (itemsList.isEmpty())
            throw new RepositoryException(itemsEmpty);

        itemsList.forEach(items -> itemsDtoList.add(convertToDto(items)));

        return itemsDtoList;
    }

    @Override
    public ItemsDto findItemById(UUID itemId) throws RepositoryException {
        Optional<Items> item = itemRepository.findById(itemId);

        if (item.isEmpty())
            throw new RepositoryException(itemNotFound);

        return convertToDto(item.get());
    }

    @Override
    public List<ItemsDto> findByItemName(String itemName) throws Exception {

        //TODO validar itemName
        List<ItemsDto> itemsDtoList = new ArrayList<>();
        List<Items> itemsByItemName = itemRepository.findByItemName(itemName);

        boolean itemsListEmpty = itemsByItemName.isEmpty();
        if (itemsListEmpty)
            throw new RepositoryException(itemNotFound);

        for (Items items : itemsByItemName)
            itemsDtoList.add(convertToDto(items));

        return itemsDtoList;
    }

    @Override
    public List<ItemsDto> findItemsByItemType(String itemType) throws Exception {

        List<ItemsDto> itemsDtoList = new ArrayList<>();
        List<Items> itemsList = itemRepository.findByItemType(itemType);

        boolean itemsListEmpty = itemsList.isEmpty();
        if (itemsListEmpty)
            throw new RepositoryException(itemsEmpty);

        for (Items items : itemsList)
            itemsDtoList.add(convertToDto(items));

        return itemsDtoList;
    }

    public void saveItemData(ItemsDto itemsData) throws Exception {

        String itemName = itemsData.getItemName();
        String itemType = itemsData.getItemType();
        String productBrand = itemsData.getProductBrand();
        String captionPacking = itemsData.getCaptionPacking();

        ArrayList<Object> booleanList = new ArrayList<>();
        List<Items> itemsByItemName = itemRepository.findByItemName(itemName);

        for (Items items : itemsByItemName) {
            boolean alreadyExists = Objects.equals(items.itemType, itemType)
                    && Objects.equals(items.productBrand, productBrand)
                    && Objects.equals(items.captionPacking, captionPacking);

            booleanList.add(alreadyExists);
        }

        boolean alreadyExists = booleanList.contains(true);
        if (alreadyExists)
            throw new RepositoryException(itemAlreadyExists);

        itemValidator(itemsData);

        Items items = convertFromDto(itemsData);

        itemRepository.save(items);
    }

    @Override
    public void updateItemData(UUID itemId, ItemsDto itemsData) throws Exception {

        boolean existsById = itemRepository.existsById(itemId);
        if (!existsById)
            throw new RepositoryException(itemNotFound);

        itemValidator(itemsData);

        Items items = convertFromDto(itemsData);

        items.setId(itemId);
        itemRepository.save(items);
    }

    @Override
    public void deleteItemData(UUID itemId) throws Exception {

        boolean existsById = itemRepository.existsById(itemId);
        if (!existsById)
            throw new RepositoryException(itemNotFound);

        itemRepository.deleteById(itemId);
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