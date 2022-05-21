package com.api.payments.controller;

import com.api.payments.dto.ItemsDto;
import com.api.payments.entity.BaseEntity;
import com.api.payments.entity.Items;
import com.api.payments.services.ItemService;
import lombok.AllArgsConstructor;
import org.hibernate.service.spi.ServiceException;
import org.sonatype.aether.RepositoryException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static com.api.payments.messages.ItemMessages.*;
import static com.api.payments.messages.ReceiptMessages.badRequest;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class ItemController extends BaseEntity {

    private ItemService itemService;

    @GetMapping(path = {"/items"})
    public ResponseEntity<List<ItemsDto>> findAllItems(){

        ResponseEntity result;

        try {
            List<ItemsDto> allItems = itemService.findAllItems();
            result = new ResponseEntity<>(allItems, HttpStatus.OK);
        } catch (RepositoryException e){
            result = new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e){
            result = new ResponseEntity<>(badRequest, HttpStatus.BAD_REQUEST);
        }
        return result;
    }

    @GetMapping(path = {"/items/{id}"})
    public ResponseEntity<ItemsDto> findItemById(@PathVariable("id") UUID itemId){

        ResponseEntity result;

        try {
            ItemsDto item = itemService.findItemById(itemId);
            result = new ResponseEntity<>(item, HttpStatus.OK);
        } catch (RepositoryException e){
            result = new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e){
            result = new ResponseEntity<>(badRequest, HttpStatus.BAD_REQUEST);
        }
        return result;
    }

    @GetMapping(path = {"/items/byItemName"})
    public ResponseEntity<List<ItemsDto>> findByItemName(@RequestBody ItemsDto itemsData){

        ResponseEntity result;

        String itemName = itemsData.itemName;

        try {
            List<ItemsDto> items = itemService.findByItemName(itemName);
            result = new ResponseEntity<>(items, HttpStatus.OK);
        } catch (RepositoryException e){
            result = new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (ServiceException e){
            result = new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        } catch (Exception e){
            result = new ResponseEntity<>(badRequest, HttpStatus.BAD_REQUEST);
        }
        return result;
    }

    @GetMapping(path = {"/items/byItemType"})
    public ResponseEntity<List<ItemsDto>> findItemsByItemType(
            @RequestBody ItemsDto itemsData){

        ResponseEntity result;

        String itemType = itemsData.itemType;

        try {
            List<ItemsDto> items = itemService.findItemsByItemType(itemType);
            result = new ResponseEntity<>(items, HttpStatus.OK);
        } catch (RepositoryException e){
            result = new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e){
            result = new ResponseEntity<>(badRequest, HttpStatus.BAD_REQUEST);
        }
        return result;
    }

    @PostMapping(path = {"/items"})
    public ResponseEntity<ItemsDto> createItem(@RequestBody ItemsDto itemsData) {

        ResponseEntity result;

        try {
            itemService.saveItemData (itemsData);
            result = new ResponseEntity<>(itemCreated, HttpStatus.CREATED);
        } catch (RepositoryException e) {
            result = new ResponseEntity<>(
                    itemNotCreated + e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (ServiceException e) {
            result = new ResponseEntity<>(
                    itemNotCreated + e.getMessage(), HttpStatus.CONFLICT);
        } catch (Exception e) {
            result = new ResponseEntity<>(badRequest, HttpStatus.BAD_REQUEST);
        }
        return result;
    }

    @PutMapping(path = {"/items/{id}"})
    public ResponseEntity<String> updateItem(
            @PathVariable("id") UUID itemId, @RequestBody ItemsDto itemsData){

        ResponseEntity<String> result;

        try {
            itemService.updateItemData(itemId, itemsData);
            result = new ResponseEntity<>(itemDataUpdated, HttpStatus.OK);
        } catch (RepositoryException e){
            result = new ResponseEntity<>(
                    itemDataNotUpdated + e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (ServiceException e){
            result = new ResponseEntity<>(
                    itemDataNotUpdated + e.getMessage(), HttpStatus.CONFLICT);
        } catch (Exception e){
            result = new ResponseEntity<>(badRequest, HttpStatus.BAD_REQUEST);
        }
        return result;
    }

    @DeleteMapping(path = {"/items/{id}"})
    public ResponseEntity<String> deleteItem(@PathVariable("id") UUID itemId) {

        ResponseEntity<String> result;

        try {
            itemService.deleteItemData(itemId);
            result = new ResponseEntity<>(itemDataDeleted, HttpStatus.OK);
        } catch (RepositoryException e) {
            result = new ResponseEntity<>(
                    itemDataNotDeleted + e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            result = new ResponseEntity<>(badRequest, HttpStatus.BAD_REQUEST);
        }
        return result;
    }
}
