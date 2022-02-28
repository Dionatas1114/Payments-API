package com.api.payments.services;

import com.api.payments.model.ReceiptModel;
import com.api.payments.repository.ReceiptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import static com.api.payments.validations.ReceiptValidator.receiptValidator;

@Service
public class ReceiptService {

    @Autowired
    public ReceiptRepository receiptRepository;

    public void saveReceiptData (ReceiptModel receiptData) throws Exception {

        String debtorFullName = receiptData.getDebtorFullName ();
        String debtorLastName = receiptData.getDebtorLastName ();
        String paymentMethod = receiptData.getPaymentMethod ();
        Boolean paymentStatus = receiptData.getPaymentStatus ();
        LocalDate paymentDate = receiptData.getPaymentDate ();
        LocalDate expirationDate = receiptData.getExpirationDate ();
        String currency = receiptData.getCurrency ();
        double interest = receiptData.getInterest ();
        double fine = receiptData.getFine ();
        double increasedValue = receiptData.getIncreasedValue ();
        double discPayAdvance = receiptData.getDiscPayAdvance ();
        double originalValue = receiptData.getOriginalValue ();
        double total = receiptData.getTotal ();
        String description = receiptData.getDescription ();
        String messageText = receiptData.getMessageText ();

        receiptValidator (
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

        receiptRepository.save (receiptData);
    }
}
