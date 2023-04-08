package com.api.payments.controller;

import com.api.payments.config.SecurityConfig;
import com.api.payments.dto.TransactionDto;
import com.api.payments.services.ReceiptService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import lombok.val;
import org.hibernate.service.spi.ServiceException;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
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
    private SecurityConfig securityConfig;

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
                    @ApiResponse(code = 400, message = "Bad Request"),
                    @ApiResponse(code = 401, message = "Unauthorized Access"),
                    @ApiResponse(code = 404, message = "No Receipts Registered")
            })
    @GetMapping(path = {"/receipts"})
    public ResponseEntity<List<TransactionDto>> findAllReceipts(@RequestHeader (name = HttpHeaders.AUTHORIZATION) String token){

        ResponseEntity result;

        try {
            securityConfig.validateToken(token);
            val allReceipts = receiptService.findAllReceipts();
            result = new ResponseEntity<>(allReceipts, HttpStatus.OK);
        } catch (ExceptionInInitializerError e){
            result = new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e){
            result = new ResponseEntity<>(badRequest, HttpStatus.BAD_REQUEST);
        }
        return result;
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
                    @ApiResponse(code = 400, message = "Bad Request"),
                    @ApiResponse(code = 401, message = "Unauthorized Access"),
                    @ApiResponse(code = 404, message = "Receipt Not Found")
            })
    @GetMapping(path = {"/receipts/{id}"})
    public ResponseEntity<TransactionDto> findReceiptById(@PathVariable("id") UUID receiptId){

        ResponseEntity result;

        try {
            val receipt = receiptService.findReceiptById(receiptId);
            result = new ResponseEntity<>(receipt, HttpStatus.OK);
        } catch (ExceptionInInitializerError e){
            result = new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e){
            result = new ResponseEntity<>(badRequest, HttpStatus.BAD_REQUEST);
        }
        return result;
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
                    @ApiResponse(code = 400, message = "Bad Request"),
                    @ApiResponse(code = 401, message = "Unauthorized Access"),
                    @ApiResponse(code = 404, message = "Receipt Not Found")
            })
    @GetMapping(path = {"/receipts/byDebtorFullName/{debtorFullName}"})
    public ResponseEntity<TransactionDto> findByDebtorFullName(@PathVariable String debtorFullName){

        ResponseEntity result;

        try {
            val receipts = receiptService.findByDebtorFullName(debtorFullName);
            result = new ResponseEntity<>(receipts, HttpStatus.OK);
        } catch (ExceptionInInitializerError e){
            result = new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e){
            result = new ResponseEntity<>(badRequest, HttpStatus.BAD_REQUEST);
        }
        return result;
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
                    @ApiResponse(code = 400, message = "Bad Request"),
                    @ApiResponse(code = 401, message = "Unauthorized Access"),
                    @ApiResponse(code = 404, message = "Receipt Not Found")
            })
    @GetMapping(path = {"/receipts/byPaymentStatus/{paymentStatus}"})
    public ResponseEntity<TransactionDto> findByPaymentStatus(@PathVariable boolean paymentStatus){

        ResponseEntity result;

        try {
            val receipts = receiptService.findByPaymentStatus(paymentStatus);
            result = new ResponseEntity<>(receipts, HttpStatus.OK);
        } catch (ExceptionInInitializerError e){
            result = new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e){
            result = new ResponseEntity<>(badRequest, HttpStatus.BAD_REQUEST);
        }
        return result;
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
                    @ApiResponse(code = 400, message = "Bad Request"),
                    @ApiResponse(code = 401, message = "Unauthorized Access"),
                    @ApiResponse(code = 404, message = "Receipt Not Found")
            })
    @GetMapping(path = {"/receipts/byPaymentMethod/{paymentMethod}"})
    public ResponseEntity<TransactionDto> findByPaymentMethod(@PathVariable String paymentMethod){

        ResponseEntity result;

        try {
            val receipts = receiptService.findByPaymentMethod(paymentMethod);
            result = new ResponseEntity<>(receipts, HttpStatus.OK);
        } catch (ExceptionInInitializerError e){
            result = new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e){
            result = new ResponseEntity<>(badRequest, HttpStatus.BAD_REQUEST);
        }
        return result;
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
                    @ApiResponse(code = 400, message = "Bad Request"),
                    @ApiResponse(code = 401, message = "Unauthorized Access"),
                    @ApiResponse(code = 404, message = "Receipt Not Found")
            })
    @GetMapping(path = {"/receipts/byExpirationDate/{expirationDate}"})
    public ResponseEntity<TransactionDto> findReceiptsByExpirationDate(
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate expirationDate){

        ResponseEntity result;

        try {
            val receipts = receiptService.findByExpirationDate(expirationDate);
            result = new ResponseEntity<>(receipts, HttpStatus.OK);
        } catch (ExceptionInInitializerError e){
            result = new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e){
            result = new ResponseEntity<>(badRequest, HttpStatus.BAD_REQUEST);
        }
        return result;
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
                    @ApiResponse(code = 400, message = "Bad Request"),
                    @ApiResponse(code = 401, message = "Unauthorized Access"),
                    @ApiResponse(code = 409, message = "Conflict")
            })
    @PostMapping(path = {"/receipts"})
    public ResponseEntity<TransactionDto> createReceipt(@RequestBody TransactionDto receiptsData) {

        ResponseEntity result;

        try {
            receiptService.saveReceiptData (receiptsData);
            result = new ResponseEntity<>(receiptDataInserted, HttpStatus.CREATED);
        } catch (ServiceException e) {
            result = new ResponseEntity<>(
                    receiptDataNotInserted + e.getMessage(), HttpStatus.CONFLICT);
        } catch (Exception e) {
            result = new ResponseEntity<>(badRequest, HttpStatus.BAD_REQUEST);
        }
        return result;
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
                    @ApiResponse(code = 400, message = "Bad Request"),
                    @ApiResponse(code = 401, message = "Unauthorized Access"),
                    @ApiResponse(code = 404, message = "Receipt Not Found"),
                    @ApiResponse(code = 409, message = "Conflict")
            })
    @PutMapping(path = {"/receipts/{id}"})
    public ResponseEntity<String> updateReceipt(
            @PathVariable("id") UUID receiptId,
            @RequestBody TransactionDto receiptsData){

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
        } catch (Exception e){
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
                    @ApiResponse(code = 400, message = "Bad Request"),
                    @ApiResponse(code = 401, message = "Unauthorized Access"),
                    @ApiResponse(code = 404, message = "Receipt Not Found")
            })
    @DeleteMapping(path = {"/receipts/{id}"})
    public ResponseEntity<String> deleteReceiptId(@PathVariable("id") UUID receiptId) {

        ResponseEntity<String> result;

        try {
            receiptService.deleteReceiptId(receiptId);
            result = new ResponseEntity<>(receiptDataDeleted, HttpStatus.OK);
        } catch (ExceptionInInitializerError e) {
            result = new ResponseEntity<>(
                    receiptDataNotDeleted + e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            result = new ResponseEntity<>(badRequest, HttpStatus.BAD_REQUEST);
        }
        return result;
    }
}
