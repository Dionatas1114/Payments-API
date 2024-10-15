package com.api.payments.controller;

import com.api.payments.dto.TransactionDto;
import com.api.payments.exception.GenericExceptionHandler;
import com.api.payments.services.PaymentService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.hibernate.service.spi.ServiceException;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static com.api.payments.messages.GenericMessages.badRequest;
import static com.api.payments.messages.GenericMessages.unauthorized;
import static com.api.payments.messages.PaymentMessages.*;

@RestController
@AllArgsConstructor
@RequestMapping("api")
@CrossOrigin("*")
public class PaymentController {

    private PaymentService paymentService;

    @ApiOperation(
            value = "Returns Data from all Payments",
            notes = "This Request Returns all Payment Data from the Database",
            tags = {"Payments"})
    @ApiResponses(
            value = {
                    @ApiResponse(
                            code = 200,
                            message = "Returns All Payment Data",
                            response = TransactionDto.class),
                    @ApiResponse(code = 400, message = badRequest),
                    @ApiResponse(code = 401, message = unauthorized),
                    @ApiResponse(code = 404, message = "No Payments Registered")
            })
    @GetMapping(path = {"/payments"})
    public ResponseEntity<?> findAllPayments() {

        try {
            List<TransactionDto> allPayments = paymentService.findAllPayments();
            return ResponseEntity.ok(allPayments);
        } catch (Exception e) {
            return GenericExceptionHandler.getException(e);
        }
    }

    @ApiOperation(
            value = "Returns Payment Data by Id",
            notes = "This Request Returns Payment Data from the Database",
            tags = {"Payments"})
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
    @GetMapping(path = {"/payments/{id}"})
    public ResponseEntity<?> findPaymentById(@PathVariable("id") UUID paymentId) {

        try {
            TransactionDto payment = paymentService.findPaymentById(paymentId);
            return ResponseEntity.ok(payment);
        } catch (Exception e) {
            return GenericExceptionHandler.getException(e);
        }
    }

    @ApiOperation(
            value = "Returns Payments Data by Expiration Date",
            notes = "This Request Returns Payments Data from the Database",
            tags = {"Payments"})
    @ApiResponses(
            value = {
                    @ApiResponse(
                            code = 200,
                            message = "Returns Payments Data",
                            response = TransactionDto.class),
                    @ApiResponse(code = 400, message = badRequest),
                    @ApiResponse(code = 401, message = unauthorized),
                    @ApiResponse(code = 404, message = "Payment Not Found")
            })
    @GetMapping(path = {"/payments/byExpirationDate/{expirationDate}"})
    public ResponseEntity<?> findPaymentsByExpirationDate(
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate expirationDate) {

        try {
            List<TransactionDto> payments = paymentService.findPaymentsByExpirationDate(expirationDate);
            return ResponseEntity.ok(payments);
        } catch (Exception e) {
            return GenericExceptionHandler.getException(e);
        }
    }

    @ApiOperation(
            value = "Returns Payments Data by Debtor Full Name",
            notes = "This Request Returns Payments Data from the Database",
            tags = {"Payments"})
    @ApiResponses(
            value = {
                    @ApiResponse(
                            code = 200,
                            message = "Returns Payments Data",
                            response = TransactionDto.class),
                    @ApiResponse(code = 400, message = badRequest),
                    @ApiResponse(code = 401, message = unauthorized),
                    @ApiResponse(code = 404, message = "Payment Not Found")
            })
    @GetMapping(path = {"/payments/byDebtorFullName/{debtorFullName}"})
    public ResponseEntity<?> findByDebtorFullName(@PathVariable String debtorFullName) {

        try {
            List<TransactionDto> payments = paymentService.findByDebtorFullName(debtorFullName);
            return ResponseEntity.ok(payments);
        } catch (Exception e) {
            return GenericExceptionHandler.getException(e);
        }
    }

    @ApiOperation(
            value = "Returns Payments Data by Payment Status",
            notes = "This Request Returns Payments Data from the Database",
            tags = {"Payments"})
    @ApiResponses(
            value = {
                    @ApiResponse(
                            code = 200,
                            message = "Returns Payments Data",
                            response = TransactionDto.class),
                    @ApiResponse(code = 400, message = badRequest),
                    @ApiResponse(code = 401, message = unauthorized),
                    @ApiResponse(code = 404, message = "Payment Not Found")
            })
    @GetMapping(path = {"/payments/byPaymentStatus/{paymentStatus}"})
    public ResponseEntity<?> findByPaymentStatus(@PathVariable boolean paymentStatus) {

        try {
            List<TransactionDto> payments = paymentService.findByPaymentStatus(paymentStatus);
            return ResponseEntity.ok(payments);
        } catch (Exception e) {
            return GenericExceptionHandler.getException(e);
        }
    }

    @ApiOperation(
            value = "Returns Payments Data by Payment Method",
            notes = "This Request Returns Payments Data from the Database",
            tags = {"Payments"})
    @ApiResponses(
            value = {
                    @ApiResponse(
                            code = 200,
                            message = "Returns Payments Data",
                            response = TransactionDto.class),
                    @ApiResponse(code = 400, message = badRequest),
                    @ApiResponse(code = 401, message = unauthorized),
                    @ApiResponse(code = 404, message = "Payment Not Found")
            })
    @GetMapping(path = {"/payments/byPaymentMethod/{paymentMethod}"})
    public ResponseEntity<?> findByPaymentMethod(@PathVariable String paymentMethod) {

        try {
            List<TransactionDto> payments = paymentService.findByPaymentMethod(paymentMethod);
            return ResponseEntity.ok(payments);
        } catch (Exception e) {
            return GenericExceptionHandler.getException(e);
        }
    }

    @ApiOperation(
            value = "Register Payment Data",
            notes = "This Request Register Payment Data in the Database",
            tags = {"Payments"})
    @ApiResponses(
            value = {
                    @ApiResponse(
                            code = 201,
                            message = "Register Payment Data",
                            response = TransactionDto.class),
                    @ApiResponse(code = 400, message = badRequest),
                    @ApiResponse(code = 401, message = unauthorized),
                    @ApiResponse(code = 409, message = "Conflict")
            })
    @PostMapping(path = {"/payments"})
    public ResponseEntity<?> createPayment(@Validated @RequestBody TransactionDto paymentsData) {

        try {
            paymentService.savePaymentData(paymentsData);
            return ResponseEntity.status(HttpStatus.CREATED).body(paymentDataInserted);
        } catch (Exception e) {
            return GenericExceptionHandler.getException(e);
        }
    }

    @ApiOperation(
            value = "Update Payment Data",
            notes = "This Request Update Payment Data in the Database",
            tags = {"Payments"})
    @ApiResponses(
            value = {
                    @ApiResponse(
                            code = 200,
                            message = "Update Payment Data",
                            response = TransactionDto.class),
                    @ApiResponse(code = 400, message = badRequest),
                    @ApiResponse(code = 401, message = unauthorized),
                    @ApiResponse(code = 404, message = "Payment Not Found"),
                    @ApiResponse(code = 409, message = "Conflict")
            })
    @PutMapping(path = {"/payments/{id}"})
    public ResponseEntity<?> updatePayment(
            @PathVariable("id") UUID paymentId,
            @Validated @RequestBody TransactionDto paymentsData) {

        try {
            paymentService.updatePayment(paymentId, paymentsData);
            return ResponseEntity.ok(paymentDataUpdated);
        } catch (ExceptionInInitializerError e) {
            return new ResponseEntity<>(paymentDataNotUpdated + e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (ServiceException e) {
            return new ResponseEntity<>(paymentDataNotUpdated + e.getMessage(), HttpStatus.CONFLICT);
        } catch (Exception e) {
            return GenericExceptionHandler.getException(e);
        }
    }

    @ApiOperation(
            value = "Delete Payment Data",
            notes = "This Request Delete Payment Data in the Database",
            tags = {"Payments"})
    @ApiResponses(
            value = {
                    @ApiResponse(
                            code = 200,
                            message = "Delete Payment Data",
                            response = TransactionDto.class),
                    @ApiResponse(code = 400, message = badRequest),
                    @ApiResponse(code = 401, message = unauthorized),
                    @ApiResponse(code = 404, message = "Payment Not Found")
            })
    @DeleteMapping(path = {"/payments/{id}"})
    public ResponseEntity<?> deletePayment(@PathVariable("id") UUID paymentId) {

        try {
            paymentService.deletePayment(paymentId);
            return ResponseEntity.ok(paymentDataDeleted);
        } catch (Exception e) {
            return GenericExceptionHandler.getException(e);
        }
    }
}
