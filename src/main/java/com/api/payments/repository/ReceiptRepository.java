package com.api.payments.repository;

import com.api.payments.model.ReceiptModel;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.*;

public interface ReceiptRepository extends JpaRepository<ReceiptModel, UUID> {

    List<ReceiptModel> findByDebtorFullName(LocalDate debtorFullName);

    List<ReceiptModel> findByPaymentStatus(LocalDate paymentStatus);

    List<ReceiptModel> findByPaymentMethod(LocalDate paymentMethod);

    List<ReceiptModel> findByExpirationDate(LocalDate expirationDate);

    boolean existsById(UUID receiptId);

    Optional<ReceiptModel> findById(UUID receiptId);

    void deleteById(UUID receiptId);
}
