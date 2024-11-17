package com.api.payments.mocks;

import com.api.payments.dto.TransactionDto;
import com.api.payments.dto.UsersDto;
import com.api.payments.entity.Payments;
import com.api.payments.entity.Users;
import com.api.payments.enums.PaymentMethods;
import com.api.payments.repository.PaymentRepository;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class PaymentsMocked {

    private static final UUID ID = UUID.fromString("4f9ab8ae-e62a-40f9-b7b8-66eb1d30b75a");
    private static final String USER_NAME = "Johann";
    private static final String PAYMENT_METHOD = PaymentMethods.CREDIT_CARD.getValue();
    private static final String DESCRIPTION = "Description";
    private static final String CURRENCY = "BRL";
    private static final double DISCOUNT = 0.0;
    private static final double PRICE = 10.00;
    private static final boolean PAYMENT_STATUS = true;
    public static final LocalDate TODAY = LocalDate.now();
    public static final LocalDate TOMORROW = TODAY.plusDays(1);

    private PaymentRepository paymentRepository;

    @Bean
    public TransactionDto returnPaymentDtoMocked(){

        UsersDto userMocked = new UsersMocked().returnUserDtoMocked();

        return TransactionDto.builder()
                .id(ID)
                .debtorFullName(USER_NAME)
                .debtorLastName(USER_NAME)
                .paymentMethod(PAYMENT_METHOD)
                .paymentStatus(PAYMENT_STATUS)
                .expirationDate(TOMORROW)
                .paymentDate(TODAY)
                .currency(CURRENCY)
                .interest(DISCOUNT)
                .fine(DISCOUNT)
                .increasedValue(DISCOUNT)
                .discPayAdvance(DISCOUNT)
                .originalValue(PRICE)
                .total(PRICE)
                .description(DESCRIPTION)
                .messageText(DESCRIPTION)
                .user(userMocked)
                .build();
    }

    @Bean
    public Payments returnPaymentMocked(){

        Users userMocked = new UsersMocked().returnUserMocked();

        Payments payment = new Payments();

        payment.setId(ID);
        payment.setDebtorFullName(USER_NAME);
        payment.setDebtorLastName(USER_NAME);
        payment.setPaymentMethod(PAYMENT_METHOD);
        payment.setPaymentStatus(PAYMENT_STATUS);
        payment.setExpirationDate(TOMORROW);
        payment.setPaymentDate(TODAY);
        payment.setTransactionFrequency(USER_NAME);
        payment.setCurrency(CURRENCY);
        payment.setInterest(DISCOUNT);
        payment.setFine(DISCOUNT);
        payment.setIncreasedValue(DISCOUNT);
        payment.setDiscPayAdvance(DISCOUNT);
        payment.setOriginalValue(PRICE);
        payment.setTotal(PRICE);
        payment.setDescription(DESCRIPTION);
        payment.setMessageText(DESCRIPTION);
        payment.setUser(userMocked);

        return payment;
    }

    @Bean
    public Optional<Payments> returnOptionalPaymentMocked() {
        return Optional.of(returnPaymentMocked());
    }

    public void paymentsDB() {
        paymentRepository.saveAll(List.of(returnPaymentMocked(), returnPaymentMocked()));
    }
}
