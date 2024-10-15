package com.api.payments.repository;

import com.api.payments.entity.Receipts;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.*;

public interface ReceiptRepository extends JpaRepository<Receipts, UUID> {

    Optional<List<Receipts>> findByDebtorFullName(String debtorFullName);
    Optional<List<Receipts>> findByPaymentStatus(boolean paymentStatus);
    Optional<List<Receipts>> findByPaymentMethod(String paymentMethod);
    Optional<List<Receipts>> findByExpirationDate(LocalDate expirationDate);

}
