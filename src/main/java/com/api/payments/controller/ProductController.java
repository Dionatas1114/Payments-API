package com.api.payments.controller;

import com.api.payments.dto.ProductsDto;
import com.api.payments.services.ProductService;
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

import static com.api.payments.messages.ProductMessages.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
@CrossOrigin("*")
public class ProductController {

    private ProductService productService;

    @ApiOperation(
            value = "Returns Data from all Products",
            notes = "This Request Returns all Product Data from the Database",
            tags = {"Products"})
    @ApiResponses(
            value = {
                    @ApiResponse(
                            code = 200,
                            message = "Return All Product Data",
                            response = ProductsDto.class),
                    @ApiResponse(code = 400, message = "Bad Request"),
                    @ApiResponse(code = 401, message = "Unauthorized Access"),
                    @ApiResponse(code = 404, message = "No Registered Products")
            })
    @GetMapping(path = {"/products"})
    public ResponseEntity<List<ProductsDto>> findAllProducts(){

        ResponseEntity result;

        try {
            List<ProductsDto> allProducts = productService.findAllProducts();
            result = new ResponseEntity<>(allProducts, HttpStatus.OK);
        } catch (RepositoryException e){
            result = new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e){
            result = new ResponseEntity<>(badRequest, HttpStatus.BAD_REQUEST);
        }
        return result;
    }

    @ApiOperation(
            value = "Return Product Data by Id",
            notes = "This Request Return Product Data from the Database",
            tags = {"Products"})
    @ApiResponses(
            value = {
                    @ApiResponse(
                            code = 200,
                            message = "Return Product Data",
                            response = ProductsDto.class),
                    @ApiResponse(code = 400, message = "Bad Request"),
                    @ApiResponse(code = 401, message = "Unauthorized Access"),
                    @ApiResponse(code = 404, message = "Product Not Found")
            })
    @GetMapping(path = {"/products/{id}"})
    public ResponseEntity<ProductsDto> findProductsById(
            @PathVariable("id") UUID productId){

        ResponseEntity result;

        try {
            ProductsDto product = productService.findProductById(productId);
            result = new ResponseEntity<>(product, HttpStatus.OK);
        } catch (RepositoryException e){
            result = new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e){
            result = new ResponseEntity<>(badRequest, HttpStatus.BAD_REQUEST);
        }
        return result;
    }

    @ApiOperation(
            value = "Return Product Data by Item Name",
            notes = "This Request Return Product Data from the Database",
            tags = {"Products"})
    @ApiResponses(
            value = {
                    @ApiResponse(
                            code = 200,
                            message = "Return Product Data",
                            response = ProductsDto.class),
                    @ApiResponse(code = 400, message = "Bad Request"),
                    @ApiResponse(code = 401, message = "Unauthorized Access"),
                    @ApiResponse(code = 404, message = "Product Not Found")
            })
    @GetMapping(path = {"/products/byItemName/{itemName}"})
    public ResponseEntity<List<ProductsDto>> findByItemName(
            @PathVariable String itemName){

        ResponseEntity result;

        try {
            List<ProductsDto> products = productService.findByItemName(itemName);
            result = new ResponseEntity<>(products, HttpStatus.OK);
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
            value = "Register Product Data",
            notes = "This Request Register Product Data in the Database",
            tags = {"Products"})
    @ApiResponses(
            value = {
                    @ApiResponse(
                            code = 200,
                            message = "Register Product Data",
                            response = ProductsDto.class),
                    @ApiResponse(code = 400, message = "Bad Request"),
                    @ApiResponse(code = 401, message = "Unauthorized Access"),
                    @ApiResponse(code = 409, message = "Conflict")
            })
    @PostMapping(path = {"/products"})
    public ResponseEntity<ProductsDto> createProduct(@RequestBody ProductsDto productsData) {

        ResponseEntity result;

        try {
            productService.saveProductData(productsData);
            result = new ResponseEntity<>(productDataInserted, HttpStatus.CREATED);
        } catch (RepositoryException e) {
            result = new ResponseEntity<>(
                    productDataNotInserted + e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (ServiceException e) {
            result = new ResponseEntity<>(
                    productDataNotInserted + e.getMessage(), HttpStatus.CONFLICT);
        } catch (Exception e) {
            result = new ResponseEntity<>(badRequest, HttpStatus.BAD_REQUEST);
        }
        return result;
    }

    @ApiOperation(
            value = "Update Product Data",
            notes = "This Request Update Product Data in the Database",
            tags = {"Products"})
    @ApiResponses(
            value = {
                    @ApiResponse(
                            code = 200,
                            message = "Update Product Data",
                            response = ProductsDto.class),
                    @ApiResponse(code = 400, message = "Bad Request"),
                    @ApiResponse(code = 401, message = "Unauthorized Access"),
                    @ApiResponse(code = 404, message = "Product Not Found"),
                    @ApiResponse(code = 409, message = "Conflict")
            })
    @PutMapping(path = {"/products/{id}"})
    public ResponseEntity<String> updateProduct(
            @PathVariable("id") UUID productId, @RequestBody ProductsDto productsData){

        ResponseEntity<String> result;

        try {
            productService.updateProductData(productId, productsData);
            result = new ResponseEntity<>(productDataUpdated, HttpStatus.OK);
        } catch (RepositoryException e){
            result = new ResponseEntity<>(
                    productDataNotUpdated + e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (ServiceException e){
            result = new ResponseEntity<>(
                    productDataNotUpdated + e.getMessage(), HttpStatus.CONFLICT);
        } catch (Exception e){
            result = new ResponseEntity<>(badRequest, HttpStatus.BAD_REQUEST);
        }
        return result;
    }

    @ApiOperation(
            value = "Delete Product Data",
            notes = "This Request Delete Product Data in the Database",
            tags = {"Products"})
    @ApiResponses(
            value = {
                    @ApiResponse(
                            code = 200,
                            message = "Delete Product Data",
                            response = ProductsDto.class),
                    @ApiResponse(code = 400, message = "Bad Request"),
                    @ApiResponse(code = 401, message = "Unauthorized Access"),
                    @ApiResponse(code = 404, message = "Product Not Found")
            })
    @DeleteMapping(path = {"/products/{id}"})
    public ResponseEntity<String> deleteProduct(@PathVariable("id") UUID productId) {

        ResponseEntity<String> result;

        try {
            productService.deleteProductData(productId);
            result = new ResponseEntity<>(productDataDeleted, HttpStatus.OK);
        } catch (RepositoryException e) {
            result = new ResponseEntity<>(
                    productDataNotDeleted + e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            result = new ResponseEntity<>(badRequest, HttpStatus.BAD_REQUEST);
        }
        return result;
    }
}
