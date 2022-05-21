package com.api.payments.services;

import com.api.payments.dto.ReceiptsDto;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public interface ReceiptService {

    List<ReceiptsDto> findAllReceipts() throws Exception;
    ReceiptsDto findReceiptById(UUID receiptId) throws Exception;
    List<ReceiptsDto> findByDebtorFullName(String debtorFullName) throws Exception;
    List<ReceiptsDto> findByPaymentStatus(boolean paymentStatus) throws Exception;
    List<ReceiptsDto> findByPaymentMethod(String paymentMethod) throws Exception;
    List<ReceiptsDto> findByExpirationDate(LocalDate expirationDate) throws Exception;
    void saveReceiptData(ReceiptsDto receiptsData) throws Exception;
    void updateReceipt(UUID receiptId, ReceiptsDto receiptsData)  throws Exception;
    void deleteReceiptId(UUID receiptId) throws Exception;
}
