package com.api.payments.repository;

import com.api.payments.model.PaymentModel;
import org.springframework.data.repository.CrudRepository;
import java.util.Optional;
import java.util.UUID;

public interface PaymentRepository extends CrudRepository<PaymentModel, UUID> {

    boolean existsById(UUID paymentId);

    Optional<PaymentModel> findById(UUID paymentId);

    void deleteById(UUID paymentId);
}
