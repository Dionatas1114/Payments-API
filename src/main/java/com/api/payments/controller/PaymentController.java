package com.api.payments.controller;

import com.api.payments.entity.Payments;
import com.api.payments.messages.PaymentMessages;
import com.api.payments.repository.PaymentRepository;
import com.api.payments.services.PaymentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/")
public class PaymentController {
    private PaymentRepository paymentRepository;
    private PaymentService paymentService;

    @GetMapping(path = {"api/payments"})
    public ResponseEntity findAllPayments(){

        ResponseEntity result;

        try {
            if (paymentRepository.count() == 0)
                result = new ResponseEntity<>(PaymentMessages.paymentsEmpty, HttpStatus.NOT_FOUND);
            else {
                Iterable<Payments> allPayments = paymentRepository.findAll();
                result = new ResponseEntity<>(allPayments, HttpStatus.OK);
            }
        } catch (Exception e){
            result = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return result;
    }

    @GetMapping(path = {"api/payments/{id}"})
    public ResponseEntity findPaymentById(@PathVariable("id") UUID paymentId){

        ResponseEntity result;

        try {
            Optional<Payments> paymentFind = paymentRepository.findById(paymentId);

            if (paymentFind.isPresent()){
                Payments payments = paymentFind.get();
                result = new ResponseEntity<>(payments, HttpStatus.OK);
            } else {
                result = new ResponseEntity<>(PaymentMessages.paymentNotFound, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e){
            result = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return result;
    }

//    List<PaymentModel> findByDebtorFullName(LocalDate debtorFullName);
//    List<PaymentModel> findByPaymentStatus(LocalDate paymentStatus);
//    List<PaymentModel> findByPaymentMethod(LocalDate paymentMethod);

    @GetMapping(path = {"api/payments/byExpirationDate"})
    public ResponseEntity<Payments> findPaymentsByExpirationDate(@RequestBody Payments paymentsData){

        ResponseEntity result;

        try {
            LocalDate expirationDate = paymentsData.expirationDate;
            List<Payments> paymentsFound = paymentRepository.findByExpirationDate(expirationDate);

            if (paymentsFound.size() > 0){
                result = new ResponseEntity<>(paymentsFound, HttpStatus.OK);
            } else {
                result = new ResponseEntity<>(PaymentMessages.paymentNotFound, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e){
            result = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return result;
    }

    @PostMapping(path = {"api/payments"})
    public ResponseEntity<Payments> createPayment(@RequestBody Payments paymentsData) {
        ResponseEntity result;

        try {
            paymentService.savePaymentData (paymentsData);
            result = new ResponseEntity<>(paymentsData, HttpStatus.CREATED);
        } catch (Exception e) {
            result = new ResponseEntity<>(PaymentMessages.paymentNotCreated, HttpStatus.BAD_REQUEST);
        }
        return result;
    }

    @PutMapping(path = {"api/payments/{id}"})
    public ResponseEntity<String> updatePayment(@PathVariable("id") UUID paymentId, @RequestBody Payments paymentsData){

        ResponseEntity<String> result;

        try {
            if (!paymentRepository.existsById(paymentId)){
                result = new ResponseEntity<>(PaymentMessages.paymentNotFound, HttpStatus.NOT_FOUND);
            } else {
                paymentsData.setId(paymentId);
                paymentService.savePaymentData (paymentsData);
                result = new ResponseEntity<>(PaymentMessages.paymentDataUpdated, HttpStatus.OK);
            }
        } catch (Exception e){
            result = new ResponseEntity<>(PaymentMessages.paymentDataNotUpdated, HttpStatus.BAD_REQUEST);
        }
        return result;
    }

    @DeleteMapping(path = {"api/payments/{id}"})
    public ResponseEntity<String> deletePayment(@PathVariable("id") UUID paymentId) {

        ResponseEntity<String> result;

        try {
            if (!paymentRepository.existsById(paymentId)) {
                result = new ResponseEntity<>(PaymentMessages.paymentNotFound, HttpStatus.NOT_FOUND);
            } else {
                paymentRepository.deleteById(paymentId);
                result = new ResponseEntity<>(PaymentMessages.paymentDataDeleted, HttpStatus.OK);
            }
        } catch (Exception e) {
            result = new ResponseEntity<>(PaymentMessages.paymentDataNotDeleted, HttpStatus.BAD_REQUEST);
        }
        return result;
    }
}
