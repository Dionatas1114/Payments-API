package com.api.payments.controller;

import com.api.payments.entity.Receipts;
import com.api.payments.messages.ReceiptMessages;
import com.api.payments.repository.ReceiptRepository;
import com.api.payments.services.ReceiptService;
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
public class ReceiptController {

    private ReceiptRepository receiptRepository;
    private ReceiptService receiptService;

    @GetMapping(path = {"api/receipts"})
    public ResponseEntity findAllReceipts(){

        ResponseEntity result;

        try {
            if (receiptRepository.count() == 0){
                result = new ResponseEntity<>(ReceiptMessages.receiptsEmpty, HttpStatus.NOT_FOUND);
            } else {
                result = new ResponseEntity<>(receiptRepository.findAll(), HttpStatus.OK);
            }
        } catch (Exception e){
            result = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return result;
    }

    @GetMapping(path = {"api/receipts/{id}"})
    public ResponseEntity findReceipt(@PathVariable("id") UUID receiptId){

        ResponseEntity result;

        try {
            Optional<Receipts> receiptFind = receiptRepository.findById(receiptId);

            if (receiptFind.isPresent()){
                result = new ResponseEntity<>(receiptFind.get(), HttpStatus.OK);
            } else {
                result = new ResponseEntity<>(ReceiptMessages.receiptNotFound, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e){
            result = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return result;
    }

//    List<ReceiptModel> findByDebtorFullName(LocalDate debtorFullName);
//    List<ReceiptModel> findByPaymentStatus(LocalDate paymentStatus);
//    List<ReceiptModel> findByPaymentMethod(LocalDate paymentMethod);

    @GetMapping(path = {"api/receipts/byExpirationDate"})
    public ResponseEntity findReceiptsByExpirationDate(@RequestBody Receipts receiptsData){

        ResponseEntity result;

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
        }
        return result;
    }

    @PostMapping(path = {"api/receipts"})
    public ResponseEntity createReceipt(@RequestBody Receipts receiptsData) {

        ResponseEntity result;

        try {
            receiptService.saveReceiptData (receiptsData);
            result = new ResponseEntity<>(receiptsData, HttpStatus.CREATED);
        } catch (Exception e) {
            result = new ResponseEntity<>(ReceiptMessages.receiptNotCreated, HttpStatus.BAD_REQUEST);
        }
        return result;
    }

    @PutMapping(path = {"api/receipts/{id}"})
    public ResponseEntity<String> updateReceipt(@PathVariable("id") UUID receiptId, @RequestBody Receipts receiptsData){

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
        }
        return result;
    }

    @DeleteMapping(path = {"api/receipts/{id}"})
    public ResponseEntity<String> deleteReceiptId(@PathVariable("id") UUID receiptId) {

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
        }
        return result;
    }
}
