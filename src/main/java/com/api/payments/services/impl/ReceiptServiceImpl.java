package com.api.payments.services.impl;

import com.api.payments.repository.ReceiptRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ReceiptServiceImpl {

    private ReceiptRepository receiptRepository;
}