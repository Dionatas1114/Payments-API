package com.api.payments.services.impl;

import com.api.payments.dto.TransactionDto;
import com.api.payments.entity.Receipts;
import com.api.payments.repository.ReceiptRepository;
import com.api.payments.services.ReceiptService;
import lombok.AllArgsConstructor;
import lombok.val;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.api.payments.messages.ReceiptMessages.*;
import static com.api.payments.validations.validators.ReceiptValidator.receiptValidator;

@Service
@AllArgsConstructor
public class ReceiptServiceImpl implements ReceiptService {

    private ReceiptRepository receiptRepository;

    @Override
    public List<TransactionDto> findAllReceipts() throws Exception {

        List<TransactionDto> receiptsList = new ArrayList<>();
        val allReceiptsList = receiptRepository.findAll();

        if (allReceiptsList.isEmpty())
            throw new ExceptionInInitializerError(noReceiptDataRegistered);

        allReceiptsList.forEach(receipt ->
                receiptsList.add(convertToDto(receipt)));

        return receiptsList;
    }

    @Override
    public TransactionDto findReceiptById(UUID receiptId) throws Exception {

        val receipt = receiptRepository.findById(receiptId);

        if (receipt.isEmpty()) throw new ExceptionInInitializerError(receiptDataNotFound);

        return convertToDto(receipt.get());
    }

    @Override
    public List<TransactionDto> findByDebtorFullName(String debtorFullName) throws Exception {

        val receipts = receiptRepository.findByDebtorFullName(debtorFullName);

        return convertToDtoList(receipts);
    }

    @Override
    public List<TransactionDto> findByPaymentStatus(boolean paymentStatus) throws Exception {

        val receipts = receiptRepository.findByPaymentStatus(paymentStatus);

        return convertToDtoList(receipts);
    }

    @Override
    public List<TransactionDto> findByPaymentMethod(String paymentMethod) throws Exception {

        val receipts = receiptRepository.findByPaymentMethod(paymentMethod);

        return convertToDtoList(receipts);
    }

    @Override
    public List<TransactionDto> findByExpirationDate(LocalDate expirationDate) throws Exception {

        val receipts = receiptRepository.findByExpirationDate(expirationDate);

        return convertToDtoList(receipts);
    }

    @Override
    public void saveReceiptData(TransactionDto receiptsData) {

        receiptValidator(receiptsData);

        receiptRepository.save(convertFromDto(receiptsData));
    }

    @Override
    public void updateReceipt(UUID receiptId, TransactionDto receiptsData) throws Exception {

        val receiptExists = receiptRepository.existsById(receiptId);

        if (!receiptExists) throw new ExceptionInInitializerError(receiptDataNotFound);

        receiptValidator(receiptsData);

        receiptsData.setId(receiptId);
        saveReceiptData(receiptsData);
    }

    @Override
    public void deleteReceiptId(UUID receiptId) throws Exception {

        val receiptExists = receiptRepository.existsById(receiptId);

        if (!receiptExists) throw new ExceptionInInitializerError(receiptDataNotFound);

        receiptRepository.deleteById(receiptId);
    }

    private TransactionDto convertToDto(Receipts receipts) {
        return new ModelMapper().map(receipts, TransactionDto.class);
    }

    private Receipts convertFromDto(TransactionDto receiptsData) {
        return new ModelMapper().map(receiptsData, Receipts.class);
    }

    private List<TransactionDto> convertToDtoList(List<Receipts> receipts) throws ExceptionInInitializerError {

        List<TransactionDto> receiptsList = new ArrayList<>();

        if (receipts.isEmpty()) throw new ExceptionInInitializerError(receiptDataNotFound);

        receipts.forEach(receipt -> receiptsList.add(convertToDto(receipt)));

        return receiptsList;
    }
}