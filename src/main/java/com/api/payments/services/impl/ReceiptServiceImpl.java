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
    public List<TransactionDto> findAllReceipts() {

        val allReceiptsList = receiptRepository.findAll();
        if (allReceiptsList.isEmpty()) throw new ExceptionInInitializerError(noReceiptDataRegistered);

        List<TransactionDto> receiptsList = new ArrayList<>();
        allReceiptsList.forEach(receipt -> receiptsList.add(convertToDto(receipt)));
        return receiptsList;
    }

    @Override
    public TransactionDto findReceiptById(UUID receiptId) {

        val receipt = receiptRepository.findById(receiptId);
        if (receipt.isEmpty()) throw new ExceptionInInitializerError(receiptDataNotFound);
        return convertToDto(receipt.get());
    }

    @Override
    public List<TransactionDto> findByDebtorFullName(String debtorFullName) {

        val receipts = receiptRepository.findByDebtorFullName(debtorFullName);
        return convertToDtoList(receipts);
    }

    @Override
    public List<TransactionDto> findByPaymentStatus(boolean paymentStatus) {

        val receipts = receiptRepository.findByPaymentStatus(paymentStatus);
        return convertToDtoList(receipts);
    }

    @Override
    public List<TransactionDto> findByPaymentMethod(String paymentMethod) {

        val receipts = receiptRepository.findByPaymentMethod(paymentMethod);
        return convertToDtoList(receipts);
    }

    @Override
    public List<TransactionDto> findByExpirationDate(LocalDate expirationDate) {

        val receipts = receiptRepository.findByExpirationDate(expirationDate);
        return convertToDtoList(receipts);
    }

    @Override
    public void saveReceiptData(TransactionDto receiptsData) {

        receiptValidator(receiptsData);
        receiptRepository.save(convertFromDto(receiptsData));
    }

    @Override
    public void updateReceipt(UUID receiptId, TransactionDto receiptsData) {

        val receiptExists = receiptRepository.existsById(receiptId);

        if (!receiptExists) throw new ExceptionInInitializerError(receiptDataNotFound);

        receiptValidator(receiptsData);

        receiptsData.setId(receiptId);
        saveReceiptData(receiptsData);
    }

    @Override
    public void deleteReceiptId(UUID receiptId) {

        val receiptExists = receiptRepository.existsById(receiptId);
        if (!receiptExists) throw new ExceptionInInitializerError(receiptDataNotFound);
        receiptRepository.deleteById(receiptId);
    }

    private List<TransactionDto> convertToDtoList(List<Receipts> receipts) throws ExceptionInInitializerError {

        if (receipts.isEmpty()) throw new ExceptionInInitializerError(receiptDataNotFound);

        List<TransactionDto> receiptsList = new ArrayList<>();
        receipts.forEach(receipt -> receiptsList.add(convertToDto(receipt)));
        return receiptsList;
    }

    private TransactionDto convertToDto(Receipts receipts) {
        return new ModelMapper().map(receipts, TransactionDto.class);
    }

    private Receipts convertFromDto(TransactionDto receiptsData) {
        return new ModelMapper().map(receiptsData, Receipts.class);
    }
}