package com.api.payments.controller;

import com.api.payments.dto.ItemsDto;
import com.api.payments.entity.BaseEntity;
import com.api.payments.entity.Items;
import com.api.payments.repository.ItemRepository;
import com.api.payments.services.ItemService;
import lombok.AllArgsConstructor;
import org.sonatype.aether.RepositoryException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.api.payments.messages.ItemMessages.*;

@RestController
@AllArgsConstructor
@RequestMapping("/")
public class ItemController extends BaseEntity {

    private ItemRepository itemRepository;
    private ItemService itemService;

    @GetMapping(path = {"api/items"})
    public ResponseEntity<List<ItemsDto>> findAllItems(){

        ResponseEntity result;

        try {
            List<ItemsDto> allItems = itemService.findAllItems();
            result = new ResponseEntity<>(allItems, HttpStatus.OK);
        } catch (RepositoryException e){
            result = new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e){
            result = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return result;
    }

    @GetMapping(path = {"api/items/{id}"})
    public ResponseEntity<ItemsDto> findItem(@PathVariable("id") UUID itemId){

        ResponseEntity result;

        try {
            ItemsDto item = itemService.findOneItems(itemId);
            result = new ResponseEntity<>(item, HttpStatus.OK);
        } catch (RepositoryException e){
            result = new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e){
            result = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return result;
    }

    @GetMapping(path = {"api/items/byItemName"})
    public ResponseEntity findItemsByItemName(@RequestBody Items itemsData){

        ResponseEntity result;

        String itemName = itemsData.itemName;

        try {
            List<Items> itemsFound = itemRepository.findByItemName(itemName);
            if (itemsFound.size() > 0){
                result = new ResponseEntity<>(itemsFound, HttpStatus.OK);
            } else {
                result = new ResponseEntity<>(itemNotFound, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e){
            result = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return result;
    }

    @GetMapping(path = {"api/items/byItemType"})
    public ResponseEntity findItemsByItemType(@RequestBody Items itemsData){

        ResponseEntity result;

        String itemType = itemsData.itemType;

        try {
            List<Items> itemsFound = itemRepository.findByItemType(itemType);
            if (itemsFound.size() > 0){
                result = new ResponseEntity<>(itemsFound, HttpStatus.OK);
            } else {
                result = new ResponseEntity<>(itemNotFound, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e){
            result = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return result;
    }

    @PostMapping(path = {"api/items"})
    public ResponseEntity<ItemsDto> createItem(@RequestBody ItemsDto itemsData) {

        ResponseEntity result;

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
                result = new ResponseEntity<>(itemAlreadyExists, HttpStatus.CONFLICT);
            } else {
                itemService.saveItemData (itemsData);
                result = new ResponseEntity<>(itemsData, HttpStatus.CREATED);
            }
        } catch (Exception e) {
            result = new ResponseEntity<>(itemNotCreated, HttpStatus.BAD_REQUEST);
            e.printStackTrace();
        }
        return result;
    }

    @PutMapping(path = {"api/items/{id}"})
    public ResponseEntity<String> updateItem(@PathVariable("id") UUID itemId, @RequestBody ItemsDto itemsData){

        ResponseEntity<String> result;

        try {
            itemService.updateItemData(itemId, itemsData);
            result = new ResponseEntity<>(itemDataUpdated, HttpStatus.OK);
        } catch (RepositoryException e){
            result = new ResponseEntity<>(itemDataNotUpdated + e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e){
            result = new ResponseEntity<>(itemDataNotUpdated + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return result;
    }

    @DeleteMapping(path = {"api/items/{id}"})
    public ResponseEntity<String> deleteItem(@PathVariable("id") UUID itemId) {

        ResponseEntity<String> result;

        try {
            itemService.deleteItemData(itemId);
            result = new ResponseEntity<>(itemDataDeleted, HttpStatus.OK);
        } catch (RepositoryException e) {
            result = new ResponseEntity<>(itemDataNotDeleted + e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            result = new ResponseEntity<>(itemDataNotDeleted + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return result;
    }
}
