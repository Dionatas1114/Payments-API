package com.api.payments.controller;

import com.api.payments.dto.TransactionDto;
import com.api.payments.exception.GenericExceptionHandler;
import com.api.payments.services.ReceiptService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.hibernate.service.spi.ServiceException;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static com.api.payments.messages.GenericMessages.*;
import static com.api.payments.messages.ReceiptMessages.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
@CrossOrigin("*")
public class ReceiptController {

    private ReceiptService receiptService;

    @ApiOperation(
            value = "Returns Data from all Receipts",
            notes = "This Request Returns all Receipt Data from the Database",
            tags = {"Receipts"})
    @ApiResponses(
            value = {
                    @ApiResponse(
                            code = 200,
                            message = "Return All Receipt Data",
                            response = TransactionDto.class),
                    @ApiResponse(code = 400, message = badRequest),
                    @ApiResponse(code = 401, message = unauthorized),
                    @ApiResponse(code = 404, message = "No Receipts Registered")
            })
    @GetMapping(path = {"/receipts"})
    public ResponseEntity<?> findAllReceipts() {

        try {
            List<TransactionDto> allReceipts = receiptService.findAllReceipts();
            return ResponseEntity.ok(allReceipts);
        } catch (Exception e) {
            return GenericExceptionHandler.getException(e);
        }
    }

    @ApiOperation(
            value = "Returns Receipt Data by Id",
            notes = "This Request Returns Receipt Data from the Database",
            tags = {"Receipts"})
    @ApiResponses(
            value = {
                    @ApiResponse(
                            code = 200,
                            message = "Return Receipt Data",
                            response = TransactionDto.class),
                    @ApiResponse(code = 400, message = badRequest),
                    @ApiResponse(code = 401, message = unauthorized),
                    @ApiResponse(code = 404, message = "Receipt Not Found")
            })
    @GetMapping(path = {"/receipts/{id}"})
    public ResponseEntity<?> findReceiptById(@PathVariable("id") UUID receiptId) {

        try {
            TransactionDto receipt = receiptService.findReceiptById(receiptId);
            return ResponseEntity.ok(receipt);
        } catch (Exception e) {
            return GenericExceptionHandler.getException(e);
        }
    }

    @ApiOperation(
            value = "Returns Receipt Data by Debtor Full Name",
            notes = "This Request Returns Receipt Data from the Database",
            tags = {"Receipts"})
    @ApiResponses(
            value = {
                    @ApiResponse(
                            code = 200,
                            message = "Return Receipt Data",
                            response = TransactionDto.class),
                    @ApiResponse(code = 400, message = badRequest),
                    @ApiResponse(code = 401, message = unauthorized),
                    @ApiResponse(code = 404, message = "Receipt Not Found")
            })
    @GetMapping(path = {"/receipts/byDebtorFullName/{debtorFullName}"})
    public ResponseEntity<?> findByDebtorFullName(@PathVariable String debtorFullName) {

        try {
            List<TransactionDto> receipts = receiptService.findByDebtorFullName(debtorFullName);
            return ResponseEntity.ok(receipts);
        } catch (Exception e) {
            return GenericExceptionHandler.getException(e);
        }
    }

    @ApiOperation(
            value = "Returns Receipt Data by Payment Status",
            notes = "This Request Returns Receipt Data from the Database",
            tags = {"Receipts"})
    @ApiResponses(
            value = {
                    @ApiResponse(
                            code = 200,
                            message = "Return Receipt Data",
                            response = TransactionDto.class),
                    @ApiResponse(code = 400, message = badRequest),
                    @ApiResponse(code = 401, message = unauthorized),
                    @ApiResponse(code = 404, message = "Receipt Not Found")
            })
    @GetMapping(path = {"/receipts/byPaymentStatus/{paymentStatus}"})
    public ResponseEntity<?> findByPaymentStatus(@PathVariable boolean paymentStatus) {

        try {
            List<TransactionDto> receipts = receiptService.findByPaymentStatus(paymentStatus);
            return ResponseEntity.ok(receipts);
        } catch (Exception e) {
            return GenericExceptionHandler.getException(e);
        }
    }

    @ApiOperation(
            value = "Returns Receipt Data by Payment Method",
            notes = "This Request Returns Receipt Data from the Database",
            tags = {"Receipts"})
    @ApiResponses(
            value = {
                    @ApiResponse(
                            code = 200,
                            message = "Return Receipt Data",
                            response = TransactionDto.class),
                    @ApiResponse(code = 400, message = badRequest),
                    @ApiResponse(code = 401, message = unauthorized),
                    @ApiResponse(code = 404, message = "Receipt Not Found")
            })
    @GetMapping(path = {"/receipts/byPaymentMethod/{paymentMethod}"})
    public ResponseEntity<?> findByPaymentMethod(@PathVariable String paymentMethod) {

        try {
            List<TransactionDto> receipts = receiptService.findByPaymentMethod(paymentMethod);
            return ResponseEntity.ok(receipts);
        } catch (Exception e) {
            return GenericExceptionHandler.getException(e);
        }
    }

    @ApiOperation(
            value = "Returns Receipt Data by Expiration Date",
            notes = "This Request Returns Receipt Data from the Database",
            tags = {"Receipts"})
    @ApiResponses(
            value = {
                    @ApiResponse(
                            code = 200,
                            message = "Return Receipt Data",
                            response = TransactionDto.class),
                    @ApiResponse(code = 400, message = badRequest),
                    @ApiResponse(code = 401, message = unauthorized),
                    @ApiResponse(code = 404, message = "Receipt Not Found")
            })
    @GetMapping(path = {"/receipts/byExpirationDate/{expirationDate}"})
    public ResponseEntity<?> findReceiptsByExpirationDate(
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate expirationDate) {

        try {
            List<TransactionDto> receipts = receiptService.findByExpirationDate(expirationDate);
            return ResponseEntity.ok(receipts);
        } catch (Exception e) {
            return GenericExceptionHandler.getException(e);
        }
    }

    @ApiOperation(
            value = "Register Receipt Data",
            notes = "This Request Register Receipt Data in the Database",
            tags = {"Receipts"})
    @ApiResponses(
            value = {
                    @ApiResponse(
                            code = 201,
                            message = "Register Receipt Data",
                            response = TransactionDto.class),
                    @ApiResponse(code = 400, message = badRequest),
                    @ApiResponse(code = 401, message = unauthorized),
                    @ApiResponse(code = 409, message = "Conflict")
            })
    @PostMapping(path = {"/receipts"})
    public ResponseEntity<?> createReceipt(@RequestBody TransactionDto receiptsData) {

        try {
            receiptService.saveReceiptData(receiptsData);
            return ResponseEntity.status(HttpStatus.CREATED).body(receiptDataInserted);
        } catch (Exception e) {
            return GenericExceptionHandler.getException(e);
        }
    }

    @ApiOperation(
            value = "Update Receipt Data",
            notes = "This Request Update Receipt Data in the Database",
            tags = {"Receipts"})
    @ApiResponses(
            value = {
                    @ApiResponse(
                            code = 200,
                            message = "Update Receipt Data",
                            response = TransactionDto.class),
                    @ApiResponse(code = 400, message = badRequest),
                    @ApiResponse(code = 401, message = unauthorized),
                    @ApiResponse(code = 404, message = "Receipt Not Found"),
                    @ApiResponse(code = 409, message = "Conflict")
            })
    @PutMapping(path = {"/receipts/{id}"})
    public ResponseEntity<?> updateReceipt(
            @PathVariable("id") UUID receiptId,
            @RequestBody TransactionDto receiptsData) {

        ResponseEntity<String> result;

        try {
            receiptService.updateReceipt(receiptId, receiptsData);
            result = new ResponseEntity<>(receiptDataUpdated, HttpStatus.OK);
        } catch (ExceptionInInitializerError e) {
            result = new ResponseEntity<>(
                    receiptDataNotUpdated + e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (ServiceException e) {
            result = new ResponseEntity<>(
                    receiptDataNotUpdated + e.getMessage(), HttpStatus.CONFLICT);
        } catch (Exception e) {
            result = new ResponseEntity<>(badRequest, HttpStatus.BAD_REQUEST);
        }
        return result;
    }

    @ApiOperation(
            value = "Delete Receipt Data",
            notes = "This Request Delete Receipt Data in the Database",
            tags = {"Receipts"})
    @ApiResponses(
            value = {
                    @ApiResponse(
                            code = 200,
                            message = "Delete Receipt Data",
                            response = TransactionDto.class),
                    @ApiResponse(code = 400, message = badRequest),
                    @ApiResponse(code = 401, message = unauthorized),
                    @ApiResponse(code = 404, message = "Receipt Not Found")
            })
    @DeleteMapping(path = {"/receipts/{id}"})
    public ResponseEntity<?> deleteReceiptId(@PathVariable("id") UUID receiptId) {

        try {
            receiptService.deleteReceiptId(receiptId);
            return ResponseEntity.ok(receiptDataDeleted);
        } catch (Exception e) {
            return GenericExceptionHandler.getException(e);
        }
    }
}
