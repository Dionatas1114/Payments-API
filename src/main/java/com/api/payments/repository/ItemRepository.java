package com.api.payments.repository;

import com.api.payments.model.ItemModel;
import org.springframework.data.repository.CrudRepository;
import java.util.Optional;
import java.util.UUID;

public interface ItemRepository extends CrudRepository<ItemModel, UUID> {

    boolean existsById(UUID itemId);

    Optional<ItemModel> findById(UUID itemId);

    void deleteById(UUID itemId);
}
