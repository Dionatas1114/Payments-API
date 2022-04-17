package com.api.payments.controller;

import com.api.payments.messages.ReceiptMessages;
import com.api.payments.entity.Receipts;
import com.api.payments.repository.ReceiptRepository;
import com.api.payments.services.ReceiptService;
import com.sun.istack.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.*;

@RestController
@RequestMapping("/")
public class ReceiptController {

    private Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    private ReceiptRepository receiptRepository;

    @Autowired
    private ReceiptService receiptService;

    @RequestMapping(path = {"api/receipts"}, method = RequestMethod.GET)
    public Object findAllReceipts(){
        logger.info("GET: /api/receipts");
        Object result;

        try {
            if (receiptRepository.count() == 0){
                result = new ResponseEntity<>(ReceiptMessages.receiptsEmpty, HttpStatus.NOT_FOUND);
            } else {
                result = new ResponseEntity<>(receiptRepository.findAll(), HttpStatus.OK);
            }
        } catch (Exception e){
            result = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
            e.printStackTrace();
        }
        return result;
    }

    @RequestMapping(path = {"api/receipts/{id}"}, method = RequestMethod.GET)
    public Object findReceipt(@PathVariable("id") UUID receiptId){
        logger.info(String.format("GET: /api/receipts/%s", receiptId));
        Object result;

        try {
            Optional<Receipts> receiptFind = receiptRepository.findById(receiptId);

            if (receiptFind.isPresent()){
                result = new ResponseEntity<>(receiptFind.get(), HttpStatus.OK);
            } else {
                result = new ResponseEntity<>(ReceiptMessages.receiptNotFound, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e){
            result = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
            e.printStackTrace();
        }
        return result;
    }

//    List<ReceiptModel> findByDebtorFullName(LocalDate debtorFullName);
//    List<ReceiptModel> findByPaymentStatus(LocalDate paymentStatus);
//    List<ReceiptModel> findByPaymentMethod(LocalDate paymentMethod);

    @RequestMapping(path = {"api/receipts/byExpirationDate"}, method = RequestMethod.GET)
    public Object findReceiptsByExpirationDate(@RequestBody Receipts receiptsData){
        logger.info("GET: /api/receipts/byExpirationDate");
        Object result;

        try {
            LocalDate expirationDate = receiptsData.expirationDate;
            List<Receipts> foundReceipts = receiptRepository.findByExpirationDate(expirationDate);

            if (foundReceipts.size() > 0){
                result = new ResponseEntity<>(foundReceipts, HttpStatus.OK);
            } else {
                result = new ResponseEntity<>(ReceiptMessages.receiptNotFound, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e){
            result = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
            e.printStackTrace();
        }
        return result;
    }

    @RequestMapping(path = {"api/receipts"}, method = RequestMethod.POST)
    public Object createReceipt(@RequestBody Receipts receiptsData) {
        logger.info("POST: /api/receipts");
        Object result;

        try {
            receiptService.saveReceiptData (receiptsData);
            result = new ResponseEntity<>(receiptsData, HttpStatus.CREATED);
        } catch (Exception e) {
            result = new ResponseEntity<>(ReceiptMessages.receiptNotCreated, HttpStatus.BAD_REQUEST);
            e.printStackTrace();
        }
        return result;
    }

    @RequestMapping(path = {"api/receipts/{id}"}, method = RequestMethod.PUT)
    public ResponseEntity<String> updateReceipt(@PathVariable("id") UUID receiptId, @RequestBody Receipts receiptsData){
        logger.info(String.format("UPDATE: /api/receipts/%s", receiptId));
        ResponseEntity<String> result;

        try {
            if (!receiptRepository.existsById(receiptId)){
                result = new ResponseEntity<>(ReceiptMessages.receiptNotFound, HttpStatus.NOT_FOUND);
            } else {
                receiptsData.setId(receiptId);
                receiptService.saveReceiptData (receiptsData);
                result = new ResponseEntity<>(ReceiptMessages.receiptDataUpdated, HttpStatus.OK);
            }
        } catch (Exception e){
            result = new ResponseEntity<>(ReceiptMessages.receiptDataNotUpdated, HttpStatus.BAD_REQUEST);
            e.printStackTrace();
        }
        return result;
    }

    @RequestMapping(path = {"api/receipts/{id}"}, method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteReceiptId(@PathVariable("id") UUID receiptId) {
        logger.info(String.format("DELETE: /api/receipts/%s", receiptId));
        ResponseEntity<String> result;

        try {
            if (!receiptRepository.existsById(receiptId)) {
                result = new ResponseEntity<>(ReceiptMessages.receiptNotFound, HttpStatus.NOT_FOUND);
            } else {
                receiptRepository.deleteById(receiptId);
                result = new ResponseEntity<>(ReceiptMessages.receiptDataDeleted, HttpStatus.OK);
            }
        } catch (Exception e) {
            result = new ResponseEntity<>(ReceiptMessages.receiptDataNotDeleted, HttpStatus.BAD_REQUEST);
            e.printStackTrace();
        }
        return result;
    }
}
