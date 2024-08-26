package com.api.payments.services.impl;

import com.api.payments.dto.TransactionDto;
import com.api.payments.entity.Receipts;
import com.api.payments.repository.ReceiptRepository;
import com.api.payments.services.ReceiptService;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.api.payments.messages.ReceiptMessages.*;
import static com.api.payments.validations.validators.ReceiptValidator.receiptValidator;

@Service
@AllArgsConstructor
public class ReceiptServiceImpl implements ReceiptService {

    private ReceiptRepository receiptRepository;

    @Override
    public List<TransactionDto> findAllReceipts() throws Exception {

        List<TransactionDto> receiptsDtoList = new ArrayList<>();

        Optional.of(receiptRepository.findAll())
                .filter(list -> !list.isEmpty())
                .orElseThrow(() -> new Exception(noReceiptDataRegistered))
                .forEach(receipt -> receiptsDtoList.add(convertToDto(receipt)));

        return receiptsDtoList;
    }

    @Override
    public TransactionDto findReceiptById(UUID receiptId) throws Exception {

        Optional<Receipts> receipt = receiptRepository.findById(receiptId);
        if (receipt.isEmpty()) throw new NotFoundException(receiptDataNotFound);
        return convertToDto(receipt.get());
    }

    @Override
    public List<TransactionDto> findByDebtorFullName(String debtorFullName) throws Exception {

        Optional<List<Receipts>> receipts = receiptRepository.findByDebtorFullName(debtorFullName);
        return convertToDtoList(receipts);
    }

    @Override
    public List<TransactionDto> findByPaymentStatus(boolean paymentStatus) throws Exception {

        Optional<List<Receipts>> receipts = receiptRepository.findByPaymentStatus(paymentStatus);
        return convertToDtoList(receipts);
    }

    @Override
    public List<TransactionDto> findByPaymentMethod(String paymentMethod) throws Exception {

        Optional<List<Receipts>> receipts = receiptRepository.findByPaymentMethod(paymentMethod);
        return convertToDtoList(receipts);
    }

    @Override
    public List<TransactionDto> findByExpirationDate(LocalDate expirationDate) throws Exception {

        Optional<List<Receipts>> receipts = receiptRepository.findByExpirationDate(expirationDate);
        return convertToDtoList(receipts);
    }

    @Override
    public void saveReceiptData(TransactionDto receiptsData) {

        receiptValidator(receiptsData);
        receiptRepository.save(convertFromDto(receiptsData));
    }

    @Override
    public void updateReceipt(UUID receiptId, TransactionDto receiptsData) throws Exception {

        receiptRepository.findById(receiptId).orElseThrow(() -> new NotFoundException(receiptDataNotFound));

        receiptsData.setId(receiptId);
        saveReceiptData(receiptsData);
    }

    @Override
    public void deleteReceiptId(UUID receiptId) throws Exception {

        receiptRepository.findById(receiptId).orElseThrow(() -> new NotFoundException(receiptDataNotFound));
        receiptRepository.deleteById(receiptId);
    }

    private List<TransactionDto> convertToDtoList(Optional<List<Receipts>> receipts) throws Exception {

        List<TransactionDto> receiptsDtoList = new ArrayList<>();

        receipts.filter(list -> !list.isEmpty())
                .orElseThrow(() -> new NotFoundException(receiptDataNotFound))
                .forEach(receipt -> receiptsDtoList.add(convertToDto(receipt)));

        return receiptsDtoList;
    }

    private TransactionDto convertToDto(Receipts receipts) {
        return new ModelMapper().map(receipts, TransactionDto.class);
    }

    private Receipts convertFromDto(TransactionDto receiptsData) {
        return new ModelMapper().map(receiptsData, Receipts.class);
    }
}