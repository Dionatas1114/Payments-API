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
import static com.api.payments.validations.PaymentValidator.paymentValidator;

@Service
@AllArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private PaymentRepository paymentRepository;

    @Override
    public List<PaymentsDto> findAllPayments() throws Exception {

        List<PaymentsDto> paymentsDtoList = new ArrayList<>();
        List<Payments> allPayments = paymentRepository.findAll();

        if (allPayments.isEmpty()) throw new RepositoryException(paymentsEmpty);

        allPayments.forEach(payment -> paymentsDtoList.add(convertToDto(payment)));

        return paymentsDtoList;
    }

    @Override
    public PaymentsDto findPaymentById(UUID paymentId) throws Exception {

        Optional<Payments> paymentFind = paymentRepository.findById(paymentId);

        if (paymentFind.isEmpty()) throw new RepositoryException(paymentNotFound);

        return convertToDto(paymentFind.get());
    }

    @Override
    public List<PaymentsDto> findPaymentsByExpirationDate(LocalDate expirationDate) throws Exception {

        List<PaymentsDto> paymentsDtoList = new ArrayList<>();
        List<Payments> paymentsFound = paymentRepository.findByExpirationDate(expirationDate);

        if (paymentsFound.isEmpty()) throw new RepositoryException(paymentNotFound);

        paymentsFound.forEach(payments -> paymentsDtoList.add(convertToDto(payments)));

        return paymentsDtoList;
    }

    @Override
    public List<PaymentsDto> findByDebtorFullName(String debtorFullName) throws RepositoryException {

        List<PaymentsDto> paymentsDtoList = new ArrayList<>();
        List<Payments> paymentsList = paymentRepository.findByDebtorFullName(debtorFullName);

        if (paymentsList.isEmpty()) throw new RepositoryException(paymentNotFound);

        paymentsList.forEach(debtor -> paymentsDtoList.add(convertToDto(debtor)));

        return paymentsDtoList;
    }

    @Override
    public List<PaymentsDto> findByPaymentStatus(boolean paymentStatus) throws RepositoryException {

        List<PaymentsDto> paymentsDtoList = new ArrayList<>();
        List<Payments> paymentsList = paymentRepository.findByPaymentStatus(paymentStatus);

        if (paymentsList.isEmpty()) throw new RepositoryException(paymentNotFound);

        paymentsList.forEach(debtor -> paymentsDtoList.add(convertToDto(debtor)));

        return paymentsDtoList;
    }

    @Override
    public List<PaymentsDto> findByPaymentMethod(String paymentMethod) throws RepositoryException {

        List<PaymentsDto> paymentsDtoList = new ArrayList<>();
        List<Payments> paymentsList = paymentRepository.findByPaymentMethod(paymentMethod);

        if (paymentsList.isEmpty()) throw new RepositoryException(paymentNotFound);

        paymentsList.forEach(debtor -> paymentsDtoList.add(convertToDto(debtor)));

        return paymentsDtoList;
    }

    @Override
    public void savePaymentData(PaymentsDto paymentsData) {

        paymentValidate(paymentsData);

        Payments payments = convertFromDto(paymentsData);

        paymentRepository.save (payments);
    }

    @Override
    public void updatePayment(UUID paymentId, PaymentsDto paymentsData) throws Exception {

        if (!paymentRepository.existsById(paymentId)) throw new RepositoryException(paymentNotFound);

        paymentValidate(paymentsData);

        paymentsData.setId(paymentId);
        savePaymentData (paymentsData);
    }

    @Override
    public void deletePayment(UUID paymentId) throws Exception {

        if (!paymentRepository.existsById(paymentId)) throw new RepositoryException(paymentNotFound);

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

    private void paymentValidate(PaymentsDto paymentsData){
        String debtorFullName = paymentsData.getDebtorFullName ();
        String debtorLastName = paymentsData.getDebtorLastName ();
        String paymentMethod = paymentsData.getPaymentMethod ();
        Boolean paymentStatus = paymentsData.getPaymentStatus ();
        LocalDate paymentDate = paymentsData.getPaymentDate ();
        LocalDate expirationDate = paymentsData.getExpirationDate ();
        String currency = paymentsData.getCurrency ();
        double interest = paymentsData.getInterest ();
        double fine = paymentsData.getFine ();
        double increasedValue = paymentsData.getIncreasedValue ();
        double discPayAdvance = paymentsData.getDiscPayAdvance ();
        double originalValue = paymentsData.getOriginalValue ();
        double total = paymentsData.getTotal ();
        String description = paymentsData.getDescription ();
        String messageText = paymentsData.getMessageText ();

        paymentValidator(
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