package com.api.payments.services;

import com.api.payments.model.PaymentModel;
import com.api.payments.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import static com.api.payments.validations.PaymentValidator.paymentValidator;

@Service
public class PaymentService {

    @Autowired
    public PaymentRepository paymentRepository;

    public void savePaymentData (PaymentModel paymentData) throws Exception {

        String debtorFullName = paymentData.getDebtorFullName ();
        String debtorLastName = paymentData.getDebtorLastName ();
        String paymentMethod = paymentData.getPaymentMethod ();
        Boolean paymentStatus = paymentData.getPaymentStatus ();
        LocalDate paymentDate = paymentData.getPaymentDate ();
        LocalDate expirationDate = paymentData.getExpirationDate ();
        String currency = paymentData.getCurrency ();
        double interest = paymentData.getInterest ();
        double fine = paymentData.getFine ();
        double increasedValue = paymentData.getIncreasedValue ();
        double discPayAdvance = paymentData.getDiscPayAdvance ();
        double originalValue = paymentData.getOriginalValue ();
        double total = paymentData.getTotal ();
        String description = paymentData.getDescription ();
        String messageText = paymentData.getMessageText ();

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

        paymentRepository.save (paymentData);
    }
}
