package com.api.payments.controller;

import com.api.payments.dto.ItemsDto;
import com.api.payments.entity.BaseEntity;
import com.api.payments.services.ItemService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
@CrossOrigin("*")
public class ItemController extends BaseEntity {

    private ItemService itemService;

    @ApiOperation(
            value = "Returns Data from all Items",
            notes = "This Request Returns all Item Data from the Database",
            tags = {"Items"})
    @ApiResponses(
            value = {
                    @ApiResponse(
                            code = 200,
                            message = "Return All Item Data",
                            response = ItemsDto.class),
                    @ApiResponse(code = 400, message = "Bad Request"),
                    @ApiResponse(code = 401, message = "Unauthorized Access"),
                    @ApiResponse(code = 404, message = "No Registered Items")
            })
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

    @ApiOperation(
            value = "Return Item Data by Id",
            notes = "This Request Return Item Data from the Database",
            tags = {"Items"})
    @ApiResponses(
            value = {
                    @ApiResponse(
                            code = 200,
                            message = "Return Item Data",
                            response = ItemsDto.class),
                    @ApiResponse(code = 400, message = "Bad Request"),
                    @ApiResponse(code = 401, message = "Unauthorized Access"),
                    @ApiResponse(code = 404, message = "Item Not Found")
            })
    @GetMapping(path = {"/items/{id}"})
    public ResponseEntity<ItemsDto> findItemById(
            @PathVariable("id") UUID itemId){

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

    @ApiOperation(
            value = "Return Item Data by Item Name",
            notes = "This Request Return Item Data from the Database",
            tags = {"Items"})
    @ApiResponses(
            value = {
                    @ApiResponse(
                            code = 200,
                            message = "Return Item Data",
                            response = ItemsDto.class),
                    @ApiResponse(code = 400, message = "Bad Request"),
                    @ApiResponse(code = 401, message = "Unauthorized Access"),
                    @ApiResponse(code = 404, message = "Item Not Found")
            })
    @GetMapping(path = {"/items/byItemName/{itemName}"})
    public ResponseEntity<List<ItemsDto>> findByItemName(
            @PathVariable String itemName){

        ResponseEntity result;

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

    @ApiOperation(
            value = "Return Item Data by Item Type",
            notes = "This Request Return Item Data from the Database",
            tags = {"Items"})
    @ApiResponses(
            value = {
                    @ApiResponse(
                            code = 200,
                            message = "Return Item Data",
                            response = ItemsDto.class),
                    @ApiResponse(code = 400, message = "Bad Request"),
                    @ApiResponse(code = 401, message = "Unauthorized Access"),
                    @ApiResponse(code = 404, message = "Item Not Found")
            })
    @GetMapping(path = {"/items/byItemType/{itemType}"})
    public ResponseEntity<List<ItemsDto>> findItemsByItemType(
            @PathVariable String itemType){

        ResponseEntity result;

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

    @ApiOperation(
            value = "Register Item Data",
            notes = "This Request Register Item Data in the Database",
            tags = {"Items"})
    @ApiResponses(
            value = {
                    @ApiResponse(
                            code = 200,
                            message = "Register Item Data",
                            response = ItemsDto.class),
                    @ApiResponse(code = 400, message = "Bad Request"),
                    @ApiResponse(code = 401, message = "Unauthorized Access"),
                    @ApiResponse(code = 409, message = "Conflict")
            })
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

    @ApiOperation(
            value = "Update Item Data",
            notes = "This Request Update Item Data in the Database",
            tags = {"Items"})
    @ApiResponses(
            value = {
                    @ApiResponse(
                            code = 200,
                            message = "Update Item Data",
                            response = ItemsDto.class),
                    @ApiResponse(code = 400, message = "Bad Request"),
                    @ApiResponse(code = 401, message = "Unauthorized Access"),
                    @ApiResponse(code = 404, message = "Item Not Found"),
                    @ApiResponse(code = 409, message = "Conflict")
            })
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

    @ApiOperation(
            value = "Delete Item Data",
            notes = "This Request Delete Item Data in the Database",
            tags = {"Items"})
    @ApiResponses(
            value = {
                    @ApiResponse(
                            code = 200,
                            message = "Delete Item Data",
                            response = ItemsDto.class),
                    @ApiResponse(code = 400, message = "Bad Request"),
                    @ApiResponse(code = 401, message = "Unauthorized Access"),
                    @ApiResponse(code = 404, message = "Item Not Found")
            })
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
