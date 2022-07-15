package com.api.payments.services.impl;

import com.api.payments.dto.PaymentsDto;
import com.api.payments.entity.Payments;
import com.api.payments.repository.PaymentRepository;
import com.api.payments.services.PaymentService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.sonatype.aether.RepositoryException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.api.payments.messages.PaymentMessages.*;
import static com.api.payments.validations.validators.PaymentValidator.paymentValidator;

@Service
@AllArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private PaymentRepository paymentRepository;

    @Override
    public List<PaymentsDto> findAllPayments() throws Exception {

        List<PaymentsDto> paymentsDtoList = new ArrayList<>();
        List<Payments> allPayments = paymentRepository.findAll();

        if (allPayments.isEmpty()) throw new RepositoryException(noPaymentDaraRegistered);

        allPayments.forEach(payment -> paymentsDtoList.add(convertToDto(payment)));

        return paymentsDtoList;
    }

    @Override
    public PaymentsDto findPaymentById(UUID paymentId) throws Exception {

        Optional<Payments> payment = paymentRepository.findById(paymentId);

        if (payment.isEmpty()) throw new RepositoryException(paymentDataNotFound);

        return convertToDto(payment.get());
    }

    @Override
    public List<PaymentsDto> findPaymentsByExpirationDate(LocalDate expirationDate) throws Exception {

        List<Payments> paymentsList = paymentRepository.findByExpirationDate(expirationDate);

        return convertToDtoList(paymentsList);
    }

    @Override
    public List<PaymentsDto> findByDebtorFullName(String debtorFullName) throws RepositoryException {

        List<Payments> paymentsList = paymentRepository.findByDebtorFullName(debtorFullName);

        return convertToDtoList(paymentsList);
    }

    @Override
    public List<PaymentsDto> findByPaymentStatus(boolean paymentStatus) throws RepositoryException {

        List<Payments> paymentsList = paymentRepository.findByPaymentStatus(paymentStatus);

        return convertToDtoList(paymentsList);
    }

    @Override
    public List<PaymentsDto> findByPaymentMethod(String paymentMethod) throws RepositoryException {

        List<Payments> paymentsList = paymentRepository.findByPaymentMethod(paymentMethod);

        return convertToDtoList(paymentsList);
    }

    @Override
    public void savePaymentData(PaymentsDto paymentsData) {

        paymentValidator(paymentsData);

        paymentRepository.save (convertFromDto(paymentsData));
    }

    @Override
    public void updatePayment(UUID paymentId, PaymentsDto paymentsData) throws Exception {

        if (!paymentRepository.existsById(paymentId)) throw new RepositoryException(paymentDataNotFound);

        paymentValidator(paymentsData);

        paymentsData.setId(paymentId);
        savePaymentData (paymentsData);
    }

    @Override
    public void deletePayment(UUID paymentId) throws Exception {

        if (!paymentRepository.existsById(paymentId)) throw new RepositoryException(paymentDataNotFound);

        paymentRepository.deleteById(paymentId);
    }

    private PaymentsDto convertToDto(Payments payments) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(payments, PaymentsDto.class);
    }

    private Payments convertFromDto(PaymentsDto payments) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(payments, Payments.class);
    }

    private List<PaymentsDto> convertToDtoList(List<Payments> paymentsFound) throws RepositoryException {

        List<PaymentsDto> paymentsDtoList = new ArrayList<>();

        if (paymentsFound.isEmpty()) throw new RepositoryException(paymentDataNotFound);

        paymentsFound.forEach(payments -> paymentsDtoList.add(convertToDto(payments)));

        return paymentsDtoList;
    }
}