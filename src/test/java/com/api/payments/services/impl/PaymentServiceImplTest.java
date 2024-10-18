package com.api.payments.services.impl;

import com.api.payments.dto.TransactionDto;
import com.api.payments.entity.Payments;
import com.api.payments.repository.PaymentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PaymentServiceImplTest {

    @MockBean
    PaymentRepository paymentRepository;

    @InjectMocks
    PaymentServiceImpl paymentServiceImpl;

    @Test
    void findPaymentById() throws Exception {
        UUID uuid = UUID.randomUUID();
        Payments payments = new Payments();

        payments.setDebtorFullName("Test");
        payments.setExpirationDate(LocalDate.now());
        payments.setPaymentMethod("Test");
        payments.setPaymentStatus(true);
        payments.setCurrency("Test");
        payments.setDebtorLastName("Test");
        payments.setFine(0.0);
        payments.setId(uuid);


        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setId(uuid);
        transactionDto.setDebtorFullName("Test");
        transactionDto.setExpirationDate(LocalDate.now());
        transactionDto.setPaymentMethod("Test");
        transactionDto.setPaymentStatus(true);
        transactionDto.setCurrency("Test");
        transactionDto.setDebtorLastName("Test");
        transactionDto.setFine(0.0);
        transactionDto.setDescription(null);
        transactionDto.setMessageText(null);

        // mock repository
        Mockito.when(paymentRepository.findById(uuid)).thenReturn(Optional.of(payments));

        // test
        TransactionDto paymentById = paymentServiceImpl.findPaymentById(uuid);

        assertEquals(transactionDto, paymentById);
    }
}