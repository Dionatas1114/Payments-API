package com.api.payments.repository;

import com.api.payments.entity.Payments;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.*;

public interface PaymentRepository extends JpaRepository<Payments, UUID> {

    List<Payments> findByDebtorFullName(LocalDate debtorFullName);
    List<Payments> findByPaymentStatus(LocalDate paymentStatus);
    List<Payments> findByPaymentMethod(LocalDate paymentMethod);
    List<Payments> findByExpirationDate(LocalDate expirationDate);

}
