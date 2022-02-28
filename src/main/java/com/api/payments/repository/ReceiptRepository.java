package com.api.payments.repository;

import com.api.payments.model.ReceiptModel;
import org.springframework.data.repository.CrudRepository;
import java.util.Optional;
import java.util.UUID;

public interface ReceiptRepository extends CrudRepository<ReceiptModel, UUID> {

    boolean existsById(UUID receiptId);

    Optional<ReceiptModel> findById(UUID receiptId);

    void deleteById(UUID receiptId);
}
