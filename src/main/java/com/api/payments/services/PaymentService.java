package com.api.payments.services;

import com.api.payments.entity.Payments;
import com.api.payments.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import static com.api.payments.validations.PaymentValidator.paymentValidator;

@Service
public class PaymentService {

    @Autowired
    public PaymentRepository paymentRepository;

    public void savePaymentData (Payments paymentsData) throws Exception {

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

        paymentRepository.save (paymentsData);
    }
}
