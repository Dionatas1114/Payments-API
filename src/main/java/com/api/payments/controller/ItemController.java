package com.api.payments.controller;

import com.api.payments.messages.ItemMessages;
import com.api.payments.model.BaseEntity;
import com.api.payments.model.ItemModel;
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
                Iterable<ItemModel> allItems = itemRepository.findAll();
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
            Optional<ItemModel> itemFind = itemRepository.findById(itemId);
            if (itemFind.isPresent()){
                ItemModel item = itemFind.get();
                result = new ResponseEntity<>(item, HttpStatus.OK);
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
    public Object findItemsByItemName(@RequestBody ItemModel itemData){
        logger.info("GET: /api/items/byItemName");
        Object result;

        String itemName = itemData.itemName;

        try {
            List<ItemModel> itemsFound = itemRepository.findByItemName(itemName);
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
    public Object findItemsByItemType(@RequestBody ItemModel itemData){
        logger.info("GET: /api/items/byItemType");
        Object result;

        String itemType = itemData.itemType;

        try {
            List<ItemModel> itemsFound = itemRepository.findByItemType(itemType);
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
    public Object createItem(@RequestBody ItemModel itemData) {
        logger.info("POST: /api/items");
        Object result;

        String itemName = itemData.itemName;
        String itemType = itemData.itemType;
        String productBrand = itemData.itemType;
        String captionPacking = itemData.itemType;

        List<ItemModel> foundItemsName = itemRepository.findByItemName(itemName);
        List<ItemModel> foundItemsType = itemRepository.findByItemType(itemType);
        List<ItemModel> foundItemsProductBrand = itemRepository.findByProductBrand(productBrand);
        List<ItemModel> foundItemsCaptionPacking = itemRepository.findByCaptionPacking(captionPacking);

        boolean itemAlreadyExists =
                foundItemsName.size() > 0
                && foundItemsType.size() > 0
                && foundItemsProductBrand.size() > 0
                && foundItemsCaptionPacking.size() > 0;

        try {
            if (itemAlreadyExists){
                result = new ResponseEntity<>(ItemMessages.itemAlreadyExists, HttpStatus.CONFLICT);
            } else {
                itemService.saveItemData (itemData);
                result = new ResponseEntity<>(itemData, HttpStatus.CREATED);
            }
        } catch (Exception e) {
            result = new ResponseEntity<>(ItemMessages.itemNotCreated, HttpStatus.BAD_REQUEST);
            e.printStackTrace();
        }
        return result;
    }

    @RequestMapping(path = {"api/items/{id}"}, method = RequestMethod.PUT)
    public ResponseEntity<String> updateItem(@PathVariable("id") UUID itemId, @RequestBody ItemModel itemData){
        logger.info(String.format("UPDATE: /api/items/%s", itemId));
        ResponseEntity<String> result;

        try {
            if (!itemRepository.existsById(itemId)){
                result = new ResponseEntity<>(ItemMessages.itemNotFound, HttpStatus.NOT_FOUND);
            } else {
                itemData.setId(itemId);
                itemService.saveItemData (itemData);
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
