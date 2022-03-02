package com.api.payments.repository;

import com.api.payments.model.ItemModel;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;

public interface ItemRepository extends JpaRepository<ItemModel, UUID> {

    List<ItemModel> findByItemName(String itemName);

    List<ItemModel> findByItemType(String itemType);

    List<ItemModel> findByProductBrand(String productBrand);

    List<ItemModel> findByCaptionPacking(String captionPacking);

    boolean existsById(UUID itemId);

    Optional<ItemModel> findById(UUID itemId);

    void deleteById(UUID itemId);
}
