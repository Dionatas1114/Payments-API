package com.api.payments.config.test;

import com.api.payments.dto.TransactionDto;
import com.api.payments.entity.Payments;
import com.api.payments.repository.PaymentRepository;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Configuration
@AllArgsConstructor
public class PaymentsMocked {

    private static final UUID ID = UUID.fromString("4f9ab8ae-e62a-40f9-b7b8-66eb1d30b75a");
    private static final String USER_NAME = "Johann";
    private static final String EMAIL = "johann@gmail.com.br";
    private static final String PASSW = "Johann1234#@";
    private static final String PHONE = "5551999999999";
    private static final boolean HAS_NOTIF = true;
    private static final String LANGUAGE = "pt_BR";

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
//        payments.setEmail(EMAIL);
//        payments.setPassword(PASSW);
//        payments.setPhone(PHONE);

        return payments;
    }

    @Bean
    public Optional<Payments> returnOptionalPaymentMocked() {
        return Optional.of(returnPaymentMocked());
    }

    public void paymentsDB() {
        val p1 = new Payments();
        val p2 = new Payments();
        paymentRepository.saveAll(List.of(p1, p2));
    }
}
