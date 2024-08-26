package com.api.payments.repository;

import com.api.payments.entity.Payments;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.*;

public interface PaymentRepository extends JpaRepository<Payments, UUID> {

    Optional<List<Payments>> findByDebtorFullName(String debtorFullName);
    Optional<List<Payments>> findByPaymentStatus(boolean paymentStatus);
    Optional<List<Payments>> findByPaymentMethod(String paymentMethod);
    Optional<List<Payments>> findByExpirationDate(LocalDate expirationDate);

}