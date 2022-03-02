package com.api.payments.controller;

import com.api.payments.messages.PaymentMessages;
import com.api.payments.model.PaymentModel;
import com.api.payments.repository.PaymentRepository;
import com.api.payments.services.PaymentService;
import com.sun.istack.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.*;

@RestController
@RequestMapping("/")
public class PaymentController {

    private final Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private PaymentService paymentService;

    @RequestMapping(path = {"api/payments"}, method = RequestMethod.GET)
    public Object findAllPayments(){
        logger.info("GET: /api/payments");
        Object result;

        try {
            if (paymentRepository.count() == 0)
                result = new ResponseEntity<>(PaymentMessages.paymentsEmpty, HttpStatus.NOT_FOUND);
            else {
                Iterable<PaymentModel> allPayments = paymentRepository.findAll();
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
            Optional<PaymentModel> paymentFind = paymentRepository.findById(paymentId);

            if (paymentFind.isPresent()){
                PaymentModel payment = paymentFind.get();
                result = new ResponseEntity<>(payment, HttpStatus.OK);
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
    public Object findPaymentsByExpirationDate(@RequestBody PaymentModel paymentData){
        logger.info("GET: /api/payments/byExpirationDate");
        Object result;

        try {
            LocalDate expirationDate = paymentData.expirationDate;
            List<PaymentModel> paymentsFound = paymentRepository.findByExpirationDate(expirationDate);

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
    public Object createPayment(@RequestBody PaymentModel paymentData) {
        Object result;
        logger.info("POST: /api/payments");

        try {
            paymentService.savePaymentData (paymentData);
            result = new ResponseEntity<>(paymentData, HttpStatus.CREATED);
        } catch (Exception e) {
            result = new ResponseEntity<>(PaymentMessages.paymentNotCreated, HttpStatus.BAD_REQUEST);
            e.printStackTrace();
        }
        return result;
    }

    @RequestMapping(path = {"api/payments/{id}"}, method = RequestMethod.PUT)
    public ResponseEntity<String> updatePayment(@PathVariable("id") UUID paymentId, @RequestBody PaymentModel paymentData){
        ResponseEntity<String> result;
        logger.info(String.format("UPDATE: /api/payments/%s", paymentId));

        try {
            if (!paymentRepository.existsById(paymentId)){
                result = new ResponseEntity<>(PaymentMessages.paymentNotFound, HttpStatus.NOT_FOUND);
            } else {
                paymentData.setId(paymentId);
                paymentService.savePaymentData (paymentData);
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
