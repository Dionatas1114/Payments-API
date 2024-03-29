package com.api.payments.controller;

import com.api.payments.config.SecurityConfig;
import com.api.payments.dto.TransactionDto;
import com.api.payments.entity.Payments;
import com.api.payments.entity.Receipts;
import com.api.payments.repository.PaymentRepository;
import com.api.payments.repository.ReceiptRepository;
import com.google.common.net.HttpHeaders;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.web.bind.annotation.*;

import javax.servlet.UnavailableException;
import java.time.LocalDate;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
@CrossOrigin("*")
public class NotificationController {

    PaymentRepository paymentRepository;
    ReceiptRepository receiptRepository;
    private SecurityConfig securityConfig;

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
                    @ApiResponse(code = 400, message = "Bad Request"),
                    @ApiResponse(code = 401, message = "Unauthorized Access"),
                    @ApiResponse(code = 404, message = "Payment Not Found")
            })
    @GetMapping(path = "/notifications/currentPayments")
    public List<Payments> getPaymentNotification(@RequestHeader(name = HttpHeaders.AUTHORIZATION) String token) throws UnavailableException {

        securityConfig.validateToken(token);
        val actualDate = LocalDate.now();
        return paymentRepository.findByExpirationDate(actualDate);
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
                    @ApiResponse(code = 400, message = "Bad Request"),
                    @ApiResponse(code = 401, message = "Unauthorized Access"),
                    @ApiResponse(code = 404, message = "Receipt Not Found")
            })
    @GetMapping(path = "/notifications/currentReceipts")
    public List<Receipts> getReceiptNotification(@RequestHeader(name = HttpHeaders.AUTHORIZATION) String token) throws UnavailableException {

        securityConfig.validateToken(token);
        val actualDate = LocalDate.now();
        return receiptRepository.findByExpirationDate(actualDate);
    }
}
