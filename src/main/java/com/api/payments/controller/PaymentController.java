package com.api.payments.controller;

import com.api.payments.dto.TransactionDto;
import com.api.payments.entity.Payments;
import com.api.payments.services.PaymentService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.hibernate.service.spi.ServiceException;
import org.sonatype.aether.RepositoryException;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

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
                    @ApiResponse(code = 400, message = "Bad Request"),
                    @ApiResponse(code = 401, message = "Unauthorized Access"),
                    @ApiResponse(code = 404, message = "No Payments Registered")
            })
    @GetMapping(path = {"/payments"})
    public ResponseEntity<List<TransactionDto>> findAllPayments(){
        ResponseEntity result;

        try {
            List<TransactionDto> allPayments = paymentService.findAllPayments();
            result = new ResponseEntity<>(allPayments, HttpStatus.OK);
        } catch (RepositoryException e){
            result = new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e){
            result = new ResponseEntity<>(badRequest, HttpStatus.BAD_REQUEST);
        }
        return result;
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
                    @ApiResponse(code = 400, message = "Bad Request"),
                    @ApiResponse(code = 401, message = "Unauthorized Access"),
                    @ApiResponse(code = 404, message = "Payment Not Found")
            })
    @GetMapping(path = {"/payments/{id}"})
    public ResponseEntity<TransactionDto> findPaymentById(
            @PathVariable("id") UUID paymentId){

        ResponseEntity result;

        try {
            TransactionDto payment = paymentService.findPaymentById(paymentId);
            result = new ResponseEntity<>(payment, HttpStatus.OK);
        } catch (RepositoryException e){
            result = new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }catch (Exception e){
            result = new ResponseEntity<>(badRequest, HttpStatus.BAD_REQUEST);
        }
        return result;
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
                    @ApiResponse(code = 400, message = "Bad Request"),
                    @ApiResponse(code = 401, message = "Unauthorized Access"),
                    @ApiResponse(code = 404, message = "Payment Not Found")
            })
    @GetMapping(path = {"/payments/byExpirationDate/{expirationDate}"})
    public ResponseEntity<List<TransactionDto>> findPaymentsByExpirationDate(
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate expirationDate){

        ResponseEntity result;

        try {
            List<TransactionDto> payments =
                    paymentService.findPaymentsByExpirationDate(expirationDate);
            result = new ResponseEntity<>(payments, HttpStatus.OK);
        } catch (RepositoryException e){
            result = new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e){
            result = new ResponseEntity<>(badRequest, HttpStatus.BAD_REQUEST);
        }
        return result;
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
                    @ApiResponse(code = 400, message = "Bad Request"),
                    @ApiResponse(code = 401, message = "Unauthorized Access"),
                    @ApiResponse(code = 404, message = "Payment Not Found")
            })
    @GetMapping(path = {"/payments/byDebtorFullName/{debtorFullName}"})
    public ResponseEntity<TransactionDto> findByDebtorFullName(
            @PathVariable String debtorFullName){

        ResponseEntity result;

        try {
            List<TransactionDto> payments =
                    paymentService.findByDebtorFullName(debtorFullName);
            result = new ResponseEntity<>(payments, HttpStatus.OK);
        } catch (RepositoryException e){
            result = new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }catch (Exception e){
            result = new ResponseEntity<>(badRequest, HttpStatus.BAD_REQUEST);
        }
        return result;
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
                    @ApiResponse(code = 400, message = "Bad Request"),
                    @ApiResponse(code = 401, message = "Unauthorized Access"),
                    @ApiResponse(code = 404, message = "Payment Not Found")
            })
    @GetMapping(path = {"/payments/byPaymentStatus/{paymentStatus}"})
    public ResponseEntity<TransactionDto> findByPaymentStatus(
            @PathVariable boolean paymentStatus){

        ResponseEntity result;

        try {
            List<TransactionDto> payments =
                    paymentService.findByPaymentStatus(paymentStatus);
            result = new ResponseEntity<>(payments, HttpStatus.OK);
        } catch (RepositoryException e){
            result = new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }catch (Exception e){
            result = new ResponseEntity<>(badRequest, HttpStatus.BAD_REQUEST);
        }
        return result;
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
                    @ApiResponse(code = 400, message = "Bad Request"),
                    @ApiResponse(code = 401, message = "Unauthorized Access"),
                    @ApiResponse(code = 404, message = "Payment Not Found")
            })
    @GetMapping(path = {"/payments/byPaymentMethod/{paymentMethod}"})
    public ResponseEntity<TransactionDto> findByPaymentMethod(
            @PathVariable String paymentMethod){

        ResponseEntity result;

        try {
            List<TransactionDto> payments =
                    paymentService.findByPaymentMethod(paymentMethod);
            result = new ResponseEntity<>(payments, HttpStatus.OK);
        } catch (RepositoryException e){
            result = new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }catch (Exception e){
            result = new ResponseEntity<>(badRequest, HttpStatus.BAD_REQUEST);
        }
        return result;
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
                    @ApiResponse(code = 400, message = "Bad Request"),
                    @ApiResponse(code = 401, message = "Unauthorized Access"),
                    @ApiResponse(code = 409, message = "Conflict")
            })
    @PostMapping(path = {"/payments"})
    public ResponseEntity<Payments> createPayment(
            @RequestBody TransactionDto paymentsData) {
        ResponseEntity result;

        try {
            paymentService.savePaymentData (paymentsData);
            result = new ResponseEntity<>(paymentDataInserted, HttpStatus.CREATED);
        } catch (ServiceException e){
            result = new ResponseEntity<>(
                    paymentDataNotInserted + e.getMessage(), HttpStatus.CONFLICT);
        } catch (Exception e) {
            result = new ResponseEntity<>(badRequest, HttpStatus.BAD_REQUEST);
        }
        return result;
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
                    @ApiResponse(code = 400, message = "Bad Request"),
                    @ApiResponse(code = 401, message = "Unauthorized Access"),
                    @ApiResponse(code = 404, message = "Payment Not Found"),
                    @ApiResponse(code = 409, message = "Conflict")
            })
    @PutMapping(path = {"/payments/{id}"})
    public ResponseEntity<String> updatePayment(
            @PathVariable("id") UUID paymentId,
            @RequestBody TransactionDto paymentsData){

        ResponseEntity<String> result;

        try {
            paymentService.updatePayment(paymentId, paymentsData);
            result = new ResponseEntity<>(paymentDataUpdated, HttpStatus.OK);
        } catch (RepositoryException e){
            result = new ResponseEntity<>(
                    paymentDataNotUpdated + e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (ServiceException e){
            result = new ResponseEntity<>(
                    paymentDataNotUpdated + e.getMessage(), HttpStatus.CONFLICT);
        } catch (Exception e){
            result = new ResponseEntity<>(badRequest, HttpStatus.BAD_REQUEST);
        }
        return result;
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
                    @ApiResponse(code = 400, message = "Bad Request"),
                    @ApiResponse(code = 401, message = "Unauthorized Access"),
                    @ApiResponse(code = 404, message = "Payment Not Found")
            })
    @DeleteMapping(path = {"/payments/{id}"})
    public ResponseEntity<String> deletePayment(@PathVariable("id") UUID paymentId) {

        ResponseEntity<String> result;

        try {
            paymentService.deletePayment(paymentId);
            result = new ResponseEntity<>(paymentDataDeleted, HttpStatus.OK);
        } catch (RepositoryException e) {
            result = new ResponseEntity<>(
                    paymentDataNotDeleted + e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            result = new ResponseEntity<>(badRequest, HttpStatus.BAD_REQUEST);
        }
        return result;
    }
}
