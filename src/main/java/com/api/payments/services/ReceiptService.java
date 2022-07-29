package com.api.payments.services;

import com.api.payments.dto.TransactionDto;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public interface ReceiptService {

    List<TransactionDto> findAllReceipts() throws Exception;
    TransactionDto findReceiptById(UUID receiptId) throws Exception;
    List<TransactionDto> findByDebtorFullName(String debtorFullName) throws Exception;
    List<TransactionDto> findByPaymentStatus(boolean paymentStatus) throws Exception;
    List<TransactionDto> findByPaymentMethod(String paymentMethod) throws Exception;
    List<TransactionDto> findByExpirationDate(LocalDate expirationDate) throws Exception;
    void saveReceiptData(TransactionDto receiptsData) throws Exception;
    void updateReceipt(UUID receiptId, TransactionDto receiptsData)  throws Exception;
    void deleteReceiptId(UUID receiptId) throws Exception;
}
