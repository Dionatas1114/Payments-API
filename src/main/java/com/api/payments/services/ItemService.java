package com.api.payments.services;

import com.api.payments.dto.ItemsDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface ItemService {

    List<ItemsDto> findAllItems() throws Exception;

    ItemsDto findOneItems(UUID itemId) throws Exception;

    void saveItemData(ItemsDto itemsData) throws Exception;
    void updateItemData(UUID itemId, ItemsDto itemsData) throws Exception;

    void deleteItemData(UUID itemId) throws Exception;

}
