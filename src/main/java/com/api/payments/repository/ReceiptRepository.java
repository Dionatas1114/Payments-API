package com.api.payments.repository;

import com.api.payments.entity.Receipts;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.*;

public interface ReceiptRepository extends JpaRepository<Receipts, UUID> {

    List<Receipts> findByDebtorFullName(LocalDate debtorFullName);
    List<Receipts> findByPaymentStatus(LocalDate paymentStatus);
    List<Receipts> findByPaymentMethod(LocalDate paymentMethod);
    List<Receipts> findByExpirationDate(LocalDate expirationDate);

}
