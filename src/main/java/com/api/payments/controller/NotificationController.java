package com.api.payments.controller;

import com.api.payments.dto.TransactionDto;
import com.api.payments.entity.Payments;
import com.api.payments.entity.Receipts;
import com.api.payments.repository.PaymentRepository;
import com.api.payments.repository.ReceiptRepository;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.api.payments.messages.GenericMessages.badRequest;
import static com.api.payments.messages.GenericMessages.unauthorized;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
@CrossOrigin("*")
public class NotificationController {

    PaymentRepository paymentRepository;
    ReceiptRepository receiptRepository;

    @ApiOperation(
            value = "Returns Payment Data by Expiration Date",
            notes = "This Request Returns Payment Data from the Database",
            tags = {"Notifications"})
    @ApiResponses(
            value = {
                    @ApiResponse(
                            code = 200,
                            message = "Returns Payment Data",
                            response = TransactionDto.class),
                    @ApiResponse(code = 400, message = badRequest),
                    @ApiResponse(code = 401, message = unauthorized),
                    @ApiResponse(code = 404, message = "Payment Not Found")
            })
    @GetMapping(path = "/notifications/currentPayments")
    public List<Payments> getPaymentNotification() {

        List<Payments> paymentsEmpty = new ArrayList<>();
        LocalDate actualDate = LocalDate.now();
        return paymentRepository.findByExpirationDate(actualDate).orElse(paymentsEmpty);
    }

    @ApiOperation(
            value = "Returns Receipt Data by Expiration Date",
            notes = "This Request Returns Receipt Data from the Database",
            tags = {"Notifications"})
    @ApiResponses(
            value = {
                    @ApiResponse(
                            code = 200,
                            message = "Returns Receipt Data",
                            response = TransactionDto.class),
                    @ApiResponse(code = 400, message = badRequest),
                    @ApiResponse(code = 401, message = unauthorized),
                    @ApiResponse(code = 404, message = "Receipt Not Found")
            })
    @GetMapping(path = "/notifications/currentReceipts")
    public List<Receipts> getReceiptNotification() {

        List<Receipts> receiptsEmpty = new ArrayList<>();
        LocalDate actualDate = LocalDate.now();
        return receiptRepository.findByExpirationDate(actualDate).orElse(receiptsEmpty);
    }
}
