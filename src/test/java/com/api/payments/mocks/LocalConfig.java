package com.api.payments.mocks;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@AllArgsConstructor
@Profile("local")
public class LocalConfig {

    private UsersMocked usersMocked;
    private ProductsMocked productsMocked;
    private ServicesMocked servicesMocked;
    private PaymentsMocked paymentsMocked;
    private ReceiptsMocked receiptsMocked;

    @Bean
    public void startDB() {
        usersMocked.usersDB();
        productsMocked.productsDB();
        servicesMocked.servicesDB();
        paymentsMocked.paymentsDB();
        receiptsMocked.receiptsDB();
    }
}
