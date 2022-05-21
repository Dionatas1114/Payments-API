package com.api.payments.repository;

import com.api.payments.entity.Receipts;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.*;

public interface ReceiptRepository extends JpaRepository<Receipts, UUID> {

    List<Receipts> findByDebtorFullName(String debtorFullName);
    List<Receipts> findByPaymentStatus(boolean paymentStatus);
    List<Receipts> findByPaymentMethod(String paymentMethod);
    List<Receipts> findByExpirationDate(LocalDate expirationDate);

}
