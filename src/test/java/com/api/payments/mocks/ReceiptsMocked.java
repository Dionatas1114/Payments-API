package com.api.payments.mocks;

import com.api.payments.dto.TransactionDto;
import com.api.payments.entity.Receipts;
import com.api.payments.repository.ReceiptRepository;
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
public class ReceiptsMocked {

    private static final UUID ID = UUID.fromString("4f9ab8ae-e62a-40f9-b7b8-66eb1d30b75a");
    private static final String USER_NAME = "Johann";
    private static final double PRICE = 10.00;
    private static final boolean PAYMENT_STATUS = true;
    public static final LocalDate TODAY = LocalDate.now();
    public static final LocalDate TOMORROW = TODAY.plusDays(1);

    private ReceiptRepository receiptRepository;

    @Bean
    public TransactionDto returnReceiptDtoMocked(){
        return TransactionDto.builder()
//                .id(ID)
//                .name(USER_NAME)
//                .email(EMAIL)
//                .password(PASSW)
//                .phone(PHONE)
//                .userConfigurations(userConfigurations)
                .build();
    }

    @Bean
    public Receipts returnReceiptMocked(){

        val receipt = new Receipts();
        receipt.setDebtorFullName(USER_NAME);
        receipt.setDebtorLastName(USER_NAME);
        receipt.setPaymentMethod(USER_NAME);
        receipt.setPaymentStatus(PAYMENT_STATUS);
        receipt.setExpirationDate(TOMORROW);
//        payments.setPaymentDate();
        receipt.setTransactionFrequency(USER_NAME);
//        payments.setCurrency();
//        payments.setInterest();
//        payments.setFine();
//        payments.setIncreasedValue();
//        payments.setDiscPayAdvance();
        receipt.setOriginalValue(PRICE);
        receipt.setTotal(PRICE);
//        payments.setDescription();
//        payments.setMessageText();
//        payments.setUser();

        return receipt;
    }

    @Bean
    public Optional<Receipts> returnOptionalReceiptMocked() {
        return Optional.of(returnReceiptMocked());
    }

    public void receiptsDB() {
        val r1 = new Receipts();
        r1.setDebtorFullName(USER_NAME);
        r1.setDebtorLastName(USER_NAME);
        r1.setPaymentMethod(USER_NAME);
        r1.setPaymentStatus(PAYMENT_STATUS);
        r1.setExpirationDate(TOMORROW);
        r1.setPaymentDate(TODAY);
        r1.setTransactionFrequency(USER_NAME);
//        r1.setCurrency();
//        r1.setInterest();
//        r1.setFine();
//        r1.setIncreasedValue();
//        r1.setDiscPayAdvance();
        r1.setOriginalValue(PRICE);
        r1.setTotal(PRICE);
//        r1.setDescription();
//        r1.setMessageText();
//        r1.setUser();

        val r2 = new Receipts();
        r2.setDebtorFullName(USER_NAME);
        r2.setDebtorLastName(USER_NAME);
        r2.setPaymentMethod(USER_NAME);
        r2.setPaymentStatus(PAYMENT_STATUS);
        r2.setExpirationDate(TOMORROW);
        r2.setPaymentDate(TODAY);
        r2.setTransactionFrequency(USER_NAME);
//        r2.setCurrency();
//        r2.setInterest();
//        r2.setFine();
//        r2.setIncreasedValue();
//        r2.setDiscPayAdvance();
        r2.setOriginalValue(PRICE);
        r2.setTotal(PRICE);
//        r2.setDescription();
//        r2.setMessageText();
//        r2.setUser();

        receiptRepository.saveAll(List.of(r1, r2));
    }
}
