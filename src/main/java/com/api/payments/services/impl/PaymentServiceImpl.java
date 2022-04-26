package com.api.payments.services.impl;

import com.api.payments.repository.PaymentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PaymentServiceImpl {

    private PaymentRepository paymentRepository;
}