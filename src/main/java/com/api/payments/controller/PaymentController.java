package com.api.payments.controller;

import com.api.payments.messages.PaymentMessages;
import com.api.payments.entity.Payments;
import com.api.payments.repository.PaymentRepository;
import com.api.payments.services.PaymentService;
import com.sun.istack.logging.Logger;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.*;

@RestController
@AllArgsConstructor
@RequestMapping("/")
public class PaymentController {

    private final Logger logger = Logger.getLogger(this.getClass());
    private PaymentRepository paymentRepository;
    private PaymentService paymentService;

    @RequestMapping(path = {"api/payments"}, method = RequestMethod.GET)
    public Object findAllPayments(){
        logger.info("GET: /api/payments");
        Object result;

        try {
            if (paymentRepository.count() == 0)
                result = new ResponseEntity<>(PaymentMessages.paymentsEmpty, HttpStatus.NOT_FOUND);
            else {
                Iterable<Payments> allPayments = paymentRepository.findAll();
                result = new ResponseEntity<>(allPayments, HttpStatus.OK);
            }
        } catch (Exception e){
            result = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
            e.printStackTrace();
        }
        return result;
    }

    @RequestMapping(path = {"api/payments/{id}"}, method = RequestMethod.GET)
    public Object findPaymentById(@PathVariable("id") UUID paymentId){
        logger.info(String.format("GET: /api/payments/%s", paymentId));
        Object result;

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
            e.printStackTrace();
        }
        return result;
    }

//    List<PaymentModel> findByDebtorFullName(LocalDate debtorFullName);
//    List<PaymentModel> findByPaymentStatus(LocalDate paymentStatus);
//    List<PaymentModel> findByPaymentMethod(LocalDate paymentMethod);

    @RequestMapping(path = {"api/payments/byExpirationDate"}, method = RequestMethod.GET)
    public Object findPaymentsByExpirationDate(@RequestBody Payments paymentsData){
        logger.info("GET: /api/payments/byExpirationDate");
        Object result;

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
            e.printStackTrace();
        }
        return result;
    }

    @RequestMapping(path = {"api/payments"}, method = RequestMethod.POST)
    public Object createPayment(@RequestBody Payments paymentsData) {
        Object result;
        logger.info("POST: /api/payments");

        try {
            paymentService.savePaymentData (paymentsData);
            result = new ResponseEntity<>(paymentsData, HttpStatus.CREATED);
        } catch (Exception e) {
            result = new ResponseEntity<>(PaymentMessages.paymentNotCreated, HttpStatus.BAD_REQUEST);
            e.printStackTrace();
        }
        return result;
    }

    @RequestMapping(path = {"api/payments/{id}"}, method = RequestMethod.PUT)
    public ResponseEntity<String> updatePayment(@PathVariable("id") UUID paymentId, @RequestBody Payments paymentsData){
        ResponseEntity<String> result;
        logger.info(String.format("UPDATE: /api/payments/%s", paymentId));

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
            e.printStackTrace();
        }
        return result;
    }

    @RequestMapping(path = {"api/payments/{id}"}, method = RequestMethod.DELETE)
    public ResponseEntity<String> deletePayment(@PathVariable("id") UUID paymentId) {
        ResponseEntity<String> result;
        logger.info(String.format("DELETE: /api/payments/%s", paymentId));

        try {
            if (!paymentRepository.existsById(paymentId)) {
                result = new ResponseEntity<>(PaymentMessages.paymentNotFound, HttpStatus.NOT_FOUND);
            } else {
                paymentRepository.deleteById(paymentId);
                result = new ResponseEntity<>(PaymentMessages.paymentDataDeleted, HttpStatus.OK);
            }
        } catch (Exception e) {
            result = new ResponseEntity<>(PaymentMessages.paymentDataNotDeleted, HttpStatus.BAD_REQUEST);
            e.printStackTrace();
        }
        return result;
    }
}
