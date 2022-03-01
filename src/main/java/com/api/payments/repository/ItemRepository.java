package com.api.payments.repository;

import com.api.payments.model.ItemModel;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.UUID;

public interface ItemRepository extends JpaRepository<ItemModel, UUID> {

    boolean existsByItemName(String itemName);

    boolean existsById(UUID itemId);

    Optional<ItemModel> findById(UUID itemId);

    void deleteById(UUID itemId);
}
