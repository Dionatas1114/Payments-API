package com.api.payments.services.impl;

import com.api.payments.dto.TransactionDto;
import com.api.payments.entity.Payments;
import com.api.payments.enums.PaymentMethods;
import com.api.payments.mocks.PaymentsMocked;
import com.api.payments.repository.PaymentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class PaymentServiceImplTest {

    @InjectMocks
    PaymentServiceImpl paymentServiceImpl; // Class under test

    @Mock
    PaymentRepository paymentRepository; // Mock repository of class under test

    private UUID paymentId;
    private Payments payments;
    private TransactionDto transactionDto;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        Payments paymentDataMocked = new PaymentsMocked().returnPaymentMocked();
        paymentId = paymentDataMocked.getId();
        payments = paymentDataMocked;

        transactionDto = new ModelMapper().map(payments, TransactionDto.class);
    }

    @Test
    @DisplayName("Should return all payments")
    void findAllPayments() throws Exception {
        Mockito.when(paymentRepository.findAll()).thenReturn(Collections.singletonList(payments));

        List<TransactionDto> allPayments = paymentServiceImpl.findAllPayments();

        Mockito.verify(paymentRepository, Mockito.times(1)).findAll();
        assertNotNull(allPayments);
        assertEquals(transactionDto, allPayments.get(0));
    }

    @Test
    @DisplayName("Should return payment by id")
    void findPaymentById() throws Exception {
        Mockito.when(paymentRepository.findById(paymentId)).thenReturn(Optional.of(payments));

        TransactionDto paymentById = paymentServiceImpl.findPaymentById(paymentId);

        Mockito.verify(paymentRepository, Mockito.times(1)).findById(paymentId);
        assertNotNull(paymentById);
        assertEquals(transactionDto, paymentById);
    }

    @Test
    @DisplayName("Should return payments by expiration date")
    void findPaymentsByExpirationDate() throws Exception {
        Mockito.when(paymentRepository.findByExpirationDate(any(LocalDate.class)))
                .thenReturn(Optional.of(Collections.singletonList(payments)));

        List<TransactionDto> paymentsByExpirationDate = paymentServiceImpl.findPaymentsByExpirationDate(LocalDate.now());

        Mockito.verify(paymentRepository, Mockito.times(1))
                .findByExpirationDate(any(LocalDate.class));
        assertNotNull(paymentsByExpirationDate);
        assertEquals(transactionDto, paymentsByExpirationDate.get(0));
    }

    @Test
    @DisplayName("Should return payments by debtor full name")
    void findByDebtorFullName() throws Exception {
        Mockito.when(paymentRepository.findByDebtorFullName(any(String.class)))
                .thenReturn(Optional.of(Collections.singletonList(payments)));

        List<TransactionDto> paymentsByDebtorFullName = paymentServiceImpl.findByDebtorFullName("debtorFullName");

        Mockito.verify(paymentRepository, Mockito.times(1))
                .findByDebtorFullName(any(String.class));
        assertNotNull(paymentsByDebtorFullName);
        assertEquals(transactionDto, paymentsByDebtorFullName.get(0));
    }

    @Test
    @DisplayName("Should return payments by payment status")
    void findByPaymentStatus() throws Exception {
        Mockito.when(paymentRepository.findByPaymentStatus(any(Boolean.class)))
                .thenReturn(Optional.of(Collections.singletonList(payments)));

        List<TransactionDto> paymentsByPaymentStatus = paymentServiceImpl.findByPaymentStatus(true);

        Mockito.verify(paymentRepository, Mockito.times(1))
                .findByPaymentStatus(any(Boolean.class));
        assertNotNull(paymentsByPaymentStatus);
        assertEquals(transactionDto, paymentsByPaymentStatus.get(0));
    }

    @Test
    @DisplayName("Should return payments by payment method")
    void findByPaymentMethod() throws Exception {
        Mockito.when(paymentRepository.findByPaymentMethod(any(String.class)))
                .thenReturn(Optional.of(Collections.singletonList(payments)));

        String cashValue = PaymentMethods.CASH.getValue();
        List<TransactionDto> paymentsByPaymentMethod = paymentServiceImpl.findByPaymentMethod(cashValue);

        Mockito.verify(paymentRepository, Mockito.times(1))
                .findByPaymentMethod(any(String.class));
        assertNotNull(paymentsByPaymentMethod);
        assertEquals(transactionDto, paymentsByPaymentMethod.get(0));
    }

    @Test
    @DisplayName("Should save payment data")
    void savePaymentData() {
        paymentServiceImpl.savePaymentData(transactionDto);

        Mockito.verify(paymentRepository, Mockito.times(1)).save(any(Payments.class));
        assertNotNull(transactionDto);
    }

    @Test
    @DisplayName("Should update payment data")
    void updatePayment() throws Exception {
        Mockito.when(paymentRepository.findById(paymentId)).thenReturn(Optional.of(payments));
        Mockito.when(paymentRepository.save(any(Payments.class))).thenReturn(payments);

        paymentServiceImpl.updatePayment(paymentId, transactionDto);

        Mockito.verify(paymentRepository, Mockito.times(1)).save(any(Payments.class));
        assertNotNull(transactionDto);
    }

    @Test
    @DisplayName("Should delete payment data")
    void deletePayment() throws Exception {
        Mockito.when(paymentRepository.findById(paymentId)).thenReturn(Optional.of(payments));

        paymentServiceImpl.deletePayment(paymentId);

        Mockito.verify(paymentRepository, Mockito.times(1)).deleteById(paymentId);
        assertNotNull(paymentId);
    }
}