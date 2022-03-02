package com.api.payments.repository;

import com.api.payments.model.PaymentModel;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.*;

public interface PaymentRepository extends JpaRepository<PaymentModel, UUID> {

    List<PaymentModel> findByDebtorFullName(LocalDate debtorFullName);

    List<PaymentModel> findByPaymentStatus(LocalDate paymentStatus);

    List<PaymentModel> findByPaymentMethod(LocalDate paymentMethod);

    List<PaymentModel> findByExpirationDate(LocalDate expirationDate);

    boolean existsById(UUID paymentId);

    Optional<PaymentModel> findById(UUID paymentId);

    void deleteById(UUID paymentId);
}
