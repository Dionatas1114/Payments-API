package com.api.payments.services.impl;

import com.api.payments.dto.TransactionDto;
import com.api.payments.entity.Payments;
import com.api.payments.repository.PaymentRepository;
import com.api.payments.services.PaymentService;
import lombok.AllArgsConstructor;
import lombok.val;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.api.payments.messages.PaymentMessages.*;
import static com.api.payments.validations.validators.PaymentValidator.paymentValidator;

@Service
@AllArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private PaymentRepository paymentRepository;

    @Override
    public List<TransactionDto> findAllPayments() throws Exception {

        List<TransactionDto> paymentsDtoList = new ArrayList<>();
        val allPayments = paymentRepository.findAll();

        if (allPayments.isEmpty()) throw new ExceptionInInitializerError(noPaymentDaraRegistered);

        allPayments.forEach(payment -> paymentsDtoList.add(convertToDto(payment)));

        return paymentsDtoList;
    }

    @Override
    public TransactionDto findPaymentById(UUID paymentId) throws Exception {

        val payment = paymentRepository.findById(paymentId);

        if (payment.isEmpty()) throw new ExceptionInInitializerError(paymentDataNotFound);

        return convertToDto(payment.get());
    }

    @Override
    public List<TransactionDto> findPaymentsByExpirationDate(LocalDate expirationDate) throws Exception {

        val paymentsList = paymentRepository.findByExpirationDate(expirationDate);

        return convertToDtoList(paymentsList);
    }

    @Override
    public List<TransactionDto> findByDebtorFullName(String debtorFullName) throws ExceptionInInitializerError {

        val paymentsList = paymentRepository.findByDebtorFullName(debtorFullName);

        return convertToDtoList(paymentsList);
    }

    @Override
    public List<TransactionDto> findByPaymentStatus(boolean paymentStatus) throws ExceptionInInitializerError {

        val paymentsList = paymentRepository.findByPaymentStatus(paymentStatus);

        return convertToDtoList(paymentsList);
    }

    @Override
    public List<TransactionDto> findByPaymentMethod(String paymentMethod) throws ExceptionInInitializerError {

        val paymentsList = paymentRepository.findByPaymentMethod(paymentMethod);

        return convertToDtoList(paymentsList);
    }

    @Override
    public void savePaymentData(TransactionDto paymentsData) {

        paymentValidator(paymentsData);

        paymentRepository.save (convertFromDto(paymentsData));
    }

    @Override
    public void updatePayment(UUID paymentId, TransactionDto paymentsData) throws Exception {

        if (!paymentRepository.existsById(paymentId)) throw new ExceptionInInitializerError(paymentDataNotFound);

        paymentValidator(paymentsData);

        paymentsData.setId(paymentId);
        savePaymentData (paymentsData);
    }

    @Override
    public void deletePayment(UUID paymentId) throws Exception {

        if (!paymentRepository.existsById(paymentId)) throw new ExceptionInInitializerError(paymentDataNotFound);

        paymentRepository.deleteById(paymentId);
    }

    private TransactionDto convertToDto(Payments payments) {
        return new ModelMapper().map(payments, TransactionDto.class);
    }

    private Payments convertFromDto(TransactionDto payments) {
        return new ModelMapper().map(payments, Payments.class);
    }

    private List<TransactionDto> convertToDtoList(List<Payments> paymentsFound) throws ExceptionInInitializerError {

        List<TransactionDto> paymentsDtoList = new ArrayList<>();

        if (paymentsFound.isEmpty()) throw new ExceptionInInitializerError(paymentDataNotFound);

        paymentsFound.forEach(payments -> paymentsDtoList.add(convertToDto(payments)));

        return paymentsDtoList;
    }
}