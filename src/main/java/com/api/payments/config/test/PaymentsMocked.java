package com.api.payments.config.test;

import com.api.payments.dto.TransactionDto;
import com.api.payments.entity.Payments;
import com.api.payments.repository.PaymentRepository;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Configuration
@AllArgsConstructor
public class PaymentsMocked {

    private static final UUID ID = UUID.fromString("4f9ab8ae-e62a-40f9-b7b8-66eb1d30b75a");
    private static final String USER_NAME = "Johann";
    private static final double PRICE = 10.00;
    private static final boolean PAYMENT_STATUS = true;
    public static final LocalDate TODAY = LocalDate.now();
    public static final LocalDate TOMORROW = TODAY.plusDays(1);

    private PaymentRepository paymentRepository;

    @Bean
    public TransactionDto returnPaymentDtoMocked(){

        return TransactionDto.builder()
                .id(ID)
//                .name(USER_NAME)
//                .email(EMAIL)
//                .password(PASSW)
//                .phone(PHONE)
                .build();
    }

    @Bean
    public Payments returnPaymentMocked(){

        val payments = new Payments();
        payments.setDebtorFullName(USER_NAME);
        payments.setDebtorLastName(USER_NAME);
        payments.setPaymentMethod(USER_NAME);
        payments.setPaymentStatus(PAYMENT_STATUS);
        payments.setExpirationDate(TOMORROW);
//        payments.setPaymentDate();
        payments.setTransactionFrequency(USER_NAME);
//        payments.setCurrency();
//        payments.setInterest();
//        payments.setFine();
//        payments.setIncreasedValue();
//        payments.setDiscPayAdvance();
        payments.setOriginalValue(PRICE);
        payments.setAmount(PRICE);
//        payments.setDescription();
//        payments.setMessageText();
//        payments.setUser();

        return payments;
    }

    @Bean
    public Optional<Payments> returnOptionalPaymentMocked() {
        return Optional.of(returnPaymentMocked());
    }

    public void paymentsDB() {
        val p1 = new Payments();
        p1.setDebtorFullName(USER_NAME);
        p1.setDebtorLastName(USER_NAME);
        p1.setPaymentMethod(USER_NAME);
        p1.setPaymentStatus(PAYMENT_STATUS);
        p1.setExpirationDate(TOMORROW);
        p1.setPaymentDate(TODAY);
        p1.setTransactionFrequency(USER_NAME);
//        payments.setCurrency();
//        payments.setInterest();
//        payments.setFine();
//        payments.setIncreasedValue();
//        payments.setDiscPayAdvance();
        p1.setOriginalValue(PRICE);
        p1.setAmount(PRICE);
//        payments.setDescription();
//        payments.setMessageText();
//        payments.setUser();

        val p2 = new Payments();
        p2.setDebtorFullName(USER_NAME);
        p2.setDebtorLastName(USER_NAME);
        p2.setPaymentMethod(USER_NAME);
        p2.setPaymentStatus(PAYMENT_STATUS);
        p2.setExpirationDate(TOMORROW);
//        p2.setPaymentDate(TODAY); // No Pay
        p2.setTransactionFrequency(USER_NAME);
//        payments.setCurrency();
//        payments.setInterest();
//        payments.setFine();
//        payments.setIncreasedValue();
//        payments.setDiscPayAdvance();
        p2.setOriginalValue(PRICE);
        p2.setAmount(PRICE);
//        payments.setDescription();
//        payments.setMessageText();
//        payments.setUser();
        paymentRepository.saveAll(List.of(p1, p2));
    }
}
