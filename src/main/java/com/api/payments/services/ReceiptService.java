package com.api.payments.services;

import com.api.payments.entity.Receipts;
import com.api.payments.repository.ReceiptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import static com.api.payments.validations.ReceiptValidator.receiptValidator;

@Service
public class ReceiptService {

    @Autowired
    public ReceiptRepository receiptRepository;

    public void saveReceiptData (Receipts receiptsData) throws Exception {

        String debtorFullName = receiptsData.getDebtorFullName ();
        String debtorLastName = receiptsData.getDebtorLastName ();
        String paymentMethod = receiptsData.getPaymentMethod ();
        Boolean paymentStatus = receiptsData.getPaymentStatus ();
        LocalDate paymentDate = receiptsData.getPaymentDate ();
        LocalDate expirationDate = receiptsData.getExpirationDate ();
        String currency = receiptsData.getCurrency ();
        double interest = receiptsData.getInterest ();
        double fine = receiptsData.getFine ();
        double increasedValue = receiptsData.getIncreasedValue ();
        double discPayAdvance = receiptsData.getDiscPayAdvance ();
        double originalValue = receiptsData.getOriginalValue ();
        double total = receiptsData.getTotal ();
        String description = receiptsData.getDescription ();
        String messageText = receiptsData.getMessageText ();

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

        receiptRepository.save (receiptsData);
    }
}
