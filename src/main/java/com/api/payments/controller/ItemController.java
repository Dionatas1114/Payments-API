package com.api.payments.controller;

import com.api.payments.messages.ItemMessages;
import com.api.payments.entity.BaseEntity;
import com.api.payments.entity.Items;
import com.api.payments.repository.ItemRepository;
import com.api.payments.services.ItemService;
import com.sun.istack.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/")
public class ItemController extends BaseEntity {

    private final Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ItemService itemService;

    @RequestMapping(path = {"api/items"}, method = RequestMethod.GET)
    public Object findAllItems(){
        logger.info("GET: /api/items");
        Object result;

        try {
            if (itemRepository.count() == 0)
                result = new ResponseEntity<>(ItemMessages.itemsEmpty, HttpStatus.NOT_FOUND);
            else {
                Iterable<Items> allItems = itemRepository.findAll();
                result = new ResponseEntity<>(allItems, HttpStatus.OK);
            }
        } catch (Exception e){
            result = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
            e.printStackTrace();
        }
        return result;
    }

    @RequestMapping(path = {"api/items/{id}"}, method = RequestMethod.GET)
    public Object findItem(@PathVariable("id") UUID itemId){
        logger.info(String.format("GET: /api/items/%s", itemId));
        Object result;

        try {
            Optional<Items> itemFind = itemRepository.findById(itemId);
            if (itemFind.isPresent()){
                Items items = itemFind.get();
                result = new ResponseEntity<>(items, HttpStatus.OK);
            } else {
                result = new ResponseEntity<>(ItemMessages.itemNotFound, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e){
            result = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
            e.printStackTrace();
        }
        return result;
    }

    @RequestMapping(path = {"api/items/byItemName"}, method = RequestMethod.GET)
    public Object findItemsByItemName(@RequestBody Items itemsData){
        logger.info("GET: /api/items/byItemName");
        Object result;

        String itemName = itemsData.itemName;

        try {
            List<Items> itemsFound = itemRepository.findByItemName(itemName);
            if (itemsFound.size() > 0){
                result = new ResponseEntity<>(itemsFound, HttpStatus.OK);
            } else {
                result = new ResponseEntity<>(ItemMessages.itemNotFound, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e){
            result = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
            e.printStackTrace();
        }
        return result;
    }

    @RequestMapping(path = {"api/items/byItemType"}, method = RequestMethod.GET)
    public Object findItemsByItemType(@RequestBody Items itemsData){
        logger.info("GET: /api/items/byItemType");
        Object result;

        String itemType = itemsData.itemType;

        try {
            List<Items> itemsFound = itemRepository.findByItemType(itemType);
            if (itemsFound.size() > 0){
                result = new ResponseEntity<>(itemsFound, HttpStatus.OK);
            } else {
                result = new ResponseEntity<>(ItemMessages.itemNotFound, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e){
            result = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
            e.printStackTrace();
        }
        return result;
    }

    @RequestMapping(path = {"api/items"}, method = RequestMethod.POST)
    public Object createItem(@RequestBody Items itemsData) {
        logger.info("POST: /api/items");
        Object result;

        String itemName = itemsData.itemName;
        String itemType = itemsData.itemType;
        String productBrand = itemsData.itemType;
        String captionPacking = itemsData.itemType;

        List<Items> foundItemsName = itemRepository.findByItemName(itemName);
        List<Items> foundItemsType = itemRepository.findByItemType(itemType);
        List<Items> foundItemsProductBrand = itemRepository.findByProductBrand(productBrand);
        List<Items> foundItemsCaptionPacking = itemRepository.findByCaptionPacking(captionPacking);

        boolean itemAlreadyExists =
                foundItemsName.size() > 0
                && foundItemsType.size() > 0
                && foundItemsProductBrand.size() > 0
                && foundItemsCaptionPacking.size() > 0;

        try {
            if (itemAlreadyExists){
                result = new ResponseEntity<>(ItemMessages.itemAlreadyExists, HttpStatus.CONFLICT);
            } else {
                itemService.saveItemData (itemsData);
                result = new ResponseEntity<>(itemsData, HttpStatus.CREATED);
            }
        } catch (Exception e) {
            result = new ResponseEntity<>(ItemMessages.itemNotCreated, HttpStatus.BAD_REQUEST);
            e.printStackTrace();
        }
        return result;
    }

    @RequestMapping(path = {"api/items/{id}"}, method = RequestMethod.PUT)
    public ResponseEntity<String> updateItem(@PathVariable("id") UUID itemId, @RequestBody Items itemsData){
        logger.info(String.format("UPDATE: /api/items/%s", itemId));
        ResponseEntity<String> result;

        try {
            if (!itemRepository.existsById(itemId)){
                result = new ResponseEntity<>(ItemMessages.itemNotFound, HttpStatus.NOT_FOUND);
            } else {
                itemsData.setId(itemId);
                itemService.saveItemData (itemsData);
                result = new ResponseEntity<>(ItemMessages.itemDataUpdated, HttpStatus.OK);
            }
        } catch (Exception e){
            result = new ResponseEntity<>(ItemMessages.itemDataNotUpdated, HttpStatus.BAD_REQUEST);
            e.printStackTrace();
        }
        return result;
    }

    @RequestMapping(path = {"api/items/{id}"}, method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteItem(@PathVariable("id") UUID itemId) {
        logger.info(String.format("DELETE: /api/items/%s", itemId));
        ResponseEntity<String> result;

        try {
            if (!itemRepository.existsById(itemId)) {
                result = new ResponseEntity<>(ItemMessages.itemNotFound, HttpStatus.NOT_FOUND);
            } else {
                itemRepository.deleteById(itemId);
                result = new ResponseEntity<>(ItemMessages.itemDataDeleted, HttpStatus.OK);
            }
        } catch (Exception e) {
            result = new ResponseEntity<>(ItemMessages.itemDataNotDeleted, HttpStatus.BAD_REQUEST);
            e.printStackTrace();
        }
        return result;
    }
}
