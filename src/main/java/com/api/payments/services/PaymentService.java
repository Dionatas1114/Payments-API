package com.api.payments.services;

import com.api.payments.dto.PaymentsDto;
import com.api.payments.entity.Payments;
import org.sonatype.aether.RepositoryException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public interface PaymentService {

    List<PaymentsDto> findAllPayments() throws Exception;
    PaymentsDto findPaymentById(UUID paymentId) throws Exception;
    List<PaymentsDto> findPaymentsByExpirationDate(LocalDate expirationDate) throws Exception;
    List<PaymentsDto> findByDebtorFullName(String debtorFullName) throws RepositoryException;
    List<PaymentsDto> findByPaymentStatus(boolean paymentStatus) throws RepositoryException;
    List<PaymentsDto> findByPaymentMethod(String paymentMethod) throws RepositoryException;
    void savePaymentData (PaymentsDto paymentsData) throws Exception;
    void updatePayment(UUID paymentId, PaymentsDto paymentsData) throws Exception;
    void deletePayment(UUID paymentId) throws Exception;
}
