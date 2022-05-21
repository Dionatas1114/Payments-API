package com.api.payments.services.impl;

import com.api.payments.dto.ReceiptsDto;
import com.api.payments.entity.Receipts;
import com.api.payments.repository.ReceiptRepository;
import com.api.payments.services.ReceiptService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.sonatype.aether.RepositoryException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.api.payments.messages.ReceiptMessages.*;
import static com.api.payments.validations.ReceiptValidator.receiptValidator;

@Service
@AllArgsConstructor
public class ReceiptServiceImpl implements ReceiptService {

    private ReceiptRepository receiptRepository;

    @Override
    public List<ReceiptsDto> findAllReceipts() throws Exception {

        List<ReceiptsDto> receiptsList = new ArrayList<>();
        List<Receipts> allReceiptsList = receiptRepository.findAll();

        if (allReceiptsList.isEmpty()) throw new RepositoryException(receiptsEmpty);

        allReceiptsList.forEach(receipt -> receiptsList.add(convertToDto(receipt)));

        return receiptsList;
    }

    @Override
    public ReceiptsDto findReceiptById(UUID receiptId) throws Exception {

        Optional<Receipts> receipt = receiptRepository.findById(receiptId);

        if (receipt.isEmpty()) throw new RepositoryException(receiptNotFound);

        return convertToDto(receipt.get());
    }

    @Override
    public List<ReceiptsDto> findByDebtorFullName(String debtorFullName) throws Exception {

        List<Receipts> receipts = receiptRepository.findByDebtorFullName(debtorFullName);

        return convertToDtoList(receipts);
    }

    @Override
    public List<ReceiptsDto> findByPaymentStatus(boolean paymentStatus) throws Exception {

        List<Receipts> receipts = receiptRepository.findByPaymentStatus(paymentStatus);

        return convertToDtoList(receipts);
    }

    @Override
    public List<ReceiptsDto> findByPaymentMethod(String paymentMethod) throws Exception {

        List<Receipts> receipts = receiptRepository.findByPaymentMethod(paymentMethod);

        return convertToDtoList(receipts);
    }

    @Override
    public List<ReceiptsDto> findByExpirationDate(LocalDate expirationDate) throws Exception {

        List<Receipts> receipts = receiptRepository.findByExpirationDate(expirationDate);

        return convertToDtoList(receipts);
    }

    @Override
    public void saveReceiptData(ReceiptsDto receiptsData) {

        receiptValidate(receiptsData);

        receiptRepository.save (converFromDto(receiptsData));
    }

    @Override
    public void updateReceipt(UUID receiptId, ReceiptsDto receiptsData) throws Exception {

        boolean receiptExists = receiptRepository.existsById(receiptId);

        if (!receiptExists) throw new RepositoryException(receiptNotFound);

        receiptValidate(receiptsData);

        receiptsData.setId(receiptId);
        saveReceiptData (receiptsData);
    }

    @Override
    public void deleteReceiptId(UUID receiptId) throws Exception {

        boolean receiptExists = receiptRepository.existsById(receiptId);

        if (!receiptExists) throw new RepositoryException(receiptNotFound);

        receiptRepository.deleteById(receiptId);
    }

    private ReceiptsDto convertToDto(Receipts receipts) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(receipts, ReceiptsDto.class);
    }

    private Receipts converFromDto(ReceiptsDto receiptsData) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(receiptsData, Receipts.class);
    }

    private List<ReceiptsDto> convertToDtoList(List<Receipts> receipts) throws RepositoryException {

        List<ReceiptsDto> receiptsList = new ArrayList<>();

        if (receipts.isEmpty()) throw new RepositoryException(receiptNotFound);

        receipts.forEach(receipt -> receiptsList.add(convertToDto(receipt)));

        return receiptsList;
    }

    private void receiptValidate(ReceiptsDto receiptsData) {

        String debtorFullName = receiptsData.getDebtorFullName ();
        String debtorLastName = receiptsData.getDebtorLastName ();
        String paymentMethod = receiptsData.getPaymentMethod ();
        Boolean paymentStatus = receiptsData.getPaymentStatus ();
        LocalDate paymentDate = receiptsData.getPaymentDate ();
        LocalDate expirationDate = receiptsData.getExpirationDate ();
        String currency = receiptsData.getCurrency ();
        double interest = receiptsData.getInterest ();
        double fine = receiptsData.getFine ();
        double increasedValue = receiptsData.getIncreasedValue ();
        double discPayAdvance = receiptsData.getDiscPayAdvance ();
        double originalValue = receiptsData.getOriginalValue ();
        double total = receiptsData.getTotal ();
        String description = receiptsData.getDescription ();
        String messageText = receiptsData.getMessageText ();

        receiptValidator (
                debtorFullName,
                debtorLastName,
                paymentMethod,
                paymentStatus,
                paymentDate,
                expirationDate,
                currency,
                interest,
                fine,
                increasedValue,
                discPayAdvance,
                originalValue,
                total,
                description,
                messageText
        );
    }
}