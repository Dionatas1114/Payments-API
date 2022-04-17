package com.api.payments.controller;

import com.api.payments.entity.Payments;
import com.api.payments.entity.Receipts;
import com.api.payments.repository.PaymentRepository;
import com.api.payments.repository.ReceiptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.time.LocalDate;
import java.util.List;

@RestController
public class NotificationController {

    @Autowired
    PaymentRepository paymentRepository;

    @Autowired
    ReceiptRepository receiptRepository;

    @GetMapping(path = "/api/notifications/currentPayments")
    public List<Payments> getPaymentNotification(){
        LocalDate actualDate = LocalDate.now();
        return paymentRepository.findByExpirationDate(actualDate);
    }

    @GetMapping(path = "/api/notifications/currentReceipts")
    public List<Receipts> getReceiptNotification(){
        LocalDate actualDate = LocalDate.now();
        return receiptRepository.findByExpirationDate(actualDate);
    }
}
