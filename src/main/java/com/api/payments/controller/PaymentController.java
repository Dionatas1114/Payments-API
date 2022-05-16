package com.api.payments.controller;

import com.api.payments.dto.PaymentsDto;
import com.api.payments.entity.Payments;
import com.api.payments.services.PaymentService;
import lombok.AllArgsConstructor;
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
@RequestMapping("api/")
public class PaymentController {

    private PaymentService paymentService;

    @GetMapping(path = {"payments"})
    public ResponseEntity<List<PaymentsDto>> findAllPayments(){
        ResponseEntity result;

        try {
            List<PaymentsDto> allPayments = paymentService.findAllPayments();
            result = new ResponseEntity<>(allPayments, HttpStatus.OK);
        } catch (RepositoryException e){
            result = new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e){
            result = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return result;
    }

    @GetMapping(path = {"payments/{id}"})
    public ResponseEntity<PaymentsDto> findPaymentById(@PathVariable("id") UUID paymentId){

        ResponseEntity result;

        try {
            PaymentsDto payment = paymentService.findPaymentById(paymentId);
            result = new ResponseEntity<>(payment, HttpStatus.OK);
        } catch (RepositoryException e){
            result = new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }catch (Exception e){
            result = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return result;
    }

    @GetMapping(path = {"payments/byExpirationDate/{expirationDate}"})
    public ResponseEntity<List<PaymentsDto>> findPaymentsByExpirationDate(
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate expirationDate){

        ResponseEntity result;

        try {
            List<PaymentsDto> paymentsFound = paymentService.findPaymentsByExpirationDate(expirationDate);
            result = new ResponseEntity<>(paymentsFound, HttpStatus.OK);
        } catch (RepositoryException e){
            result = new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e){
            result = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return result;
    }

    @GetMapping(path = {"payments/byDebtorFullName/{debtorFullName}"})
    public ResponseEntity<PaymentsDto> findByDebtorFullName(@PathVariable String debtorFullName){

        ResponseEntity result;

        try {
            List<PaymentsDto> paymentsFound = paymentService.findByDebtorFullName(debtorFullName);
            result = new ResponseEntity<>(paymentsFound, HttpStatus.OK);
        } catch (RepositoryException e){
            result = new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }catch (Exception e){
            result = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return result;
    }

    @GetMapping(path = {"payments/byPaymentStatus/{paymentStatus}"})
    public ResponseEntity<PaymentsDto> findByPaymentStatus(@PathVariable boolean paymentStatus){

        ResponseEntity result;

        try {
            List<PaymentsDto> paymentsFound = paymentService.findByPaymentStatus(paymentStatus);
            result = new ResponseEntity<>(paymentsFound, HttpStatus.OK);
        } catch (RepositoryException e){
            result = new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }catch (Exception e){
            result = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return result;
    }

    @GetMapping(path = {"payments/byPaymentMethod/{paymentMethod}"})
    public ResponseEntity<PaymentsDto> findByPaymentMethod(@PathVariable String paymentMethod){

        ResponseEntity result;

        try {
            List<PaymentsDto> paymentsFound = paymentService.findByPaymentMethod(paymentMethod);
            result = new ResponseEntity<>(paymentsFound, HttpStatus.OK);
        } catch (RepositoryException e){
            result = new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }catch (Exception e){
            result = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return result;
    }

    @PostMapping(path = {"payments"})
    public ResponseEntity<Payments> createPayment(@RequestBody PaymentsDto paymentsData) {
        ResponseEntity result;

        try {
            paymentService.savePaymentData (paymentsData);
            result = new ResponseEntity<>(paymentCreated, HttpStatus.CREATED);
        } catch (Exception e) {
            result = new ResponseEntity<>(paymentNotCreated, HttpStatus.BAD_REQUEST);
        }
        return result;
    }

    @PutMapping(path = {"payments/{id}"})
    public ResponseEntity<String> updatePayment(@PathVariable("id") UUID paymentId, @RequestBody PaymentsDto paymentsData){

        ResponseEntity<String> result;

        try {
            paymentService.updatePayment(paymentId, paymentsData);
            result = new ResponseEntity<>(paymentDataUpdated, HttpStatus.OK);
        } catch (RepositoryException e){
            result = new ResponseEntity<>(paymentNotFound, HttpStatus.NOT_FOUND);
        } catch (Exception e){
            result = new ResponseEntity<>(paymentDataNotUpdated, HttpStatus.BAD_REQUEST);
        }
        return result;
    }

    @DeleteMapping(path = {"payments/{id}"})
    public ResponseEntity<String> deletePayment(@PathVariable("id") UUID paymentId) {

        ResponseEntity<String> result;

        try {
            paymentService.deletePayment(paymentId);
            result = new ResponseEntity<>(paymentDataDeleted, HttpStatus.OK);
        } catch (Exception e) {
            result = new ResponseEntity<>(paymentDataNotDeleted, HttpStatus.BAD_REQUEST);
        }
        return result;
    }
}
