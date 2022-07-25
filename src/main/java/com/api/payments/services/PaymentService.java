package com.api.payments.services;

import com.api.payments.dto.TransactionDto;
import org.sonatype.aether.RepositoryException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public interface PaymentService {

    List<TransactionDto> findAllPayments() throws Exception;
    TransactionDto findPaymentById(UUID paymentId) throws Exception;
    List<TransactionDto> findPaymentsByExpirationDate(LocalDate expirationDate) throws Exception;
    List<TransactionDto> findByDebtorFullName(String debtorFullName) throws RepositoryException;
    List<TransactionDto> findByPaymentStatus(boolean paymentStatus) throws RepositoryException;
    List<TransactionDto> findByPaymentMethod(String paymentMethod) throws RepositoryException;
    void savePaymentData (TransactionDto paymentsData) throws Exception;
    void updatePayment(UUID paymentId, TransactionDto paymentsData) throws Exception;
    void deletePayment(UUID paymentId) throws Exception;

}
