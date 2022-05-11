package com.api.payments.repository;

import com.api.payments.entity.Items;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;

public interface ItemRepository extends JpaRepository<Items, UUID> {

    List<Items> findByItemName(String itemName);
    List<Items> findByItemType(String itemType);
    List<Items> findByProductBrand(String productBrand);
    List<Items> findByCaptionPacking(String captionPacking);

}
