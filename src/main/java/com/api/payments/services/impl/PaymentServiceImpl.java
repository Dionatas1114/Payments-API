package com.api.payments.services.impl;

import com.api.payments.dto.TransactionDto;
import com.api.payments.entity.Payments;
import com.api.payments.repository.PaymentRepository;
import com.api.payments.services.PaymentService;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

import static com.api.payments.messages.PaymentMessages.*;
import static com.api.payments.validations.validators.PaymentValidator.paymentValidator;

@Service
@AllArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private PaymentRepository paymentRepository;

    @Override
    public List<TransactionDto> findAllPayments() throws Exception {

        List<Payments> allPayments = paymentRepository.findAll();
        return convertToDtoList(Optional.of(allPayments), noPaymentDataRegistered);
    }

    @Override
    public TransactionDto findPaymentById(UUID paymentId) throws Exception {

        Payments payment = paymentRepository.findById(paymentId).orElseThrow(() -> new NotFoundException(paymentDataNotFound));
        return convertToDto(payment);
    }

    @Override
    public List<TransactionDto> findPaymentsByExpirationDate(LocalDate expirationDate) throws Exception {

        Optional<List<Payments>> paymentsList = paymentRepository.findByExpirationDate(expirationDate);
        return convertToDtoList(paymentsList, null);
    }

    @Override
    public List<TransactionDto> findByDebtorFullName(String debtorFullName) throws Exception {

        Optional<List<Payments>> paymentsList = paymentRepository.findByDebtorFullName(debtorFullName);
        return convertToDtoList(paymentsList, null);
    }

    @Override
    public List<TransactionDto> findByPaymentStatus(boolean paymentStatus) throws Exception {

        Optional<List<Payments>> paymentsList = paymentRepository.findByPaymentStatus(paymentStatus);
        return convertToDtoList(paymentsList, null);
    }

    @Override
    public List<TransactionDto> findByPaymentMethod(String paymentMethod) throws Exception {

        Optional<List<Payments>> paymentsList = paymentRepository.findByPaymentMethod(paymentMethod);
        return convertToDtoList(paymentsList, null);
    }

    @Override
    public void savePaymentData(TransactionDto paymentsData) {

        paymentValidator(paymentsData);
        paymentRepository.save(convertFromDto(paymentsData));
    }

    @Override
    public void updatePayment(UUID paymentId, TransactionDto paymentsData) throws Exception {

        paymentRepository.findById(paymentId).orElseThrow(() -> new NotFoundException(paymentDataNotFound));
        paymentValidator(paymentsData);

        paymentsData.setId(paymentId);
        savePaymentData(paymentsData);
    }

    @Override
    public void deletePayment(UUID paymentId) throws Exception {

        paymentRepository.findById(paymentId).orElseThrow(() -> new NotFoundException(paymentDataNotFound));
        paymentRepository.deleteById(paymentId);
    }

    private TransactionDto convertToDto(Payments payments) {
        return new ModelMapper().map(payments, TransactionDto.class);
    }

    private Payments convertFromDto(TransactionDto payments) {
        return new ModelMapper().map(payments, Payments.class);
    }

    private List<TransactionDto> convertToDtoList(Optional<List<Payments>> paymentsFound, String message) throws Exception {

        List<TransactionDto> paymentsDtoList = new ArrayList<>();

        String finalMessage = Objects.nonNull(message) ? message : paymentDataNotFound;

        paymentsFound
                .filter(payments -> !payments.isEmpty())
                .orElseThrow(() -> new NotFoundException(finalMessage))
                .forEach(payment -> paymentsDtoList.add(convertToDto(payment)));

        return paymentsDtoList;
    }
}