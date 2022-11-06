package com.api.payments.controller;

import com.api.payments.dto.ServicesDto;
import com.api.payments.services.ServiceService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.hibernate.service.spi.ServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static com.api.payments.messages.ServiceMessages.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
@CrossOrigin("*")
public class ServiceController {

    private ServiceService serviceService;

    @ApiOperation(
            value = "Returns Data from all Services",
            notes = "This Request Returns all Service Data from the Database",
            tags = {"Services"})
    @ApiResponses(
            value = {
                    @ApiResponse(
                            code = 200,
                            message = "Return All Service Data",
                            response = ServicesDto.class),
                    @ApiResponse(code = 400, message = "Bad Request"),
                    @ApiResponse(code = 401, message = "Unauthorized Access"),
                    @ApiResponse(code = 404, message = "No Services Registered")
            })
    @GetMapping(path = {"/services"})
    public ResponseEntity findAllServices(){

        ResponseEntity result;

        try {
            List<ServicesDto> allServices = serviceService.findAllServices();
            result = new ResponseEntity<>(allServices, HttpStatus.OK);
        } catch (ExceptionInInitializerError e){
            result = new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e){
            result = new ResponseEntity<>(badRequest, HttpStatus.BAD_REQUEST);
        }
        return result;
    }

    @ApiOperation(
            value = "Returns Service Data by Id",
            notes = "This Request Service Data from the Database",
            tags = {"Services"})
    @ApiResponses(
            value = {
                    @ApiResponse(
                            code = 200,
                            message = "Return Service Data",
                            response = ServicesDto.class),
                    @ApiResponse(code = 400, message = "Bad Request"),
                    @ApiResponse(code = 401, message = "Unauthorized Access"),
                    @ApiResponse(code = 404, message = "Service Not Found")
            })
    @GetMapping(path = {"/services/{id}"})
    public ResponseEntity findServiceById(@PathVariable("id") UUID serviceId){

        ResponseEntity result;

        try {
            ServicesDto service = serviceService.findServiceById(serviceId);
            result = new ResponseEntity<>(service, HttpStatus.OK);
        } catch (ExceptionInInitializerError e){
            result = new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e){
            result = new ResponseEntity<>(badRequest, HttpStatus.BAD_REQUEST);
        }
        return result;
    }

    @ApiOperation(
            value = "Register Service Data",
            notes = "This Request Register Service Data in the Database",
            tags = {"Services"})
    @ApiResponses(
            value = {
                    @ApiResponse(
                            code = 201,
                            message = "Register Service Data",
                            response = ServicesDto.class),
                    @ApiResponse(code = 400, message = "Bad Request"),
                    @ApiResponse(code = 401, message = "Unauthorized Access"),
                    @ApiResponse(code = 409, message = "Conflict")
            })
    @PostMapping(path = {"/services"})
    public ResponseEntity createService(@RequestBody ServicesDto servicesData){

        ResponseEntity result;

        try {
            serviceService.saveServiceData(servicesData);
            result = new ResponseEntity<>(serviceDataInserted, HttpStatus.CREATED);
        } catch (ServiceException e){
            result = new ResponseEntity<>(
                    serviceDataNotInserted + e.getMessage(), HttpStatus.CONFLICT);
        } catch (Exception e){
            result = new ResponseEntity<>(badRequest, HttpStatus.BAD_REQUEST);
        }
        return result;
    }

    @ApiOperation(
            value = "Update Service Data",
            notes = "This Request Update Service Data in the Database",
            tags = {"Services"})
    @ApiResponses(
            value = {
                    @ApiResponse(
                            code = 200,
                            message = "Update Service Data",
                            response = ServicesDto.class),
                    @ApiResponse(code = 400, message = "Bad Request"),
                    @ApiResponse(code = 401, message = "Unauthorized Access"),
                    @ApiResponse(code = 404, message = "Service Not Found"),
                    @ApiResponse(code = 409, message = "Conflict")
            })
    @PutMapping(path = {"/services/{id}"})
    public ResponseEntity<String> updateService(
            @PathVariable("id") UUID serviceId, @RequestBody ServicesDto servicesData){

        ResponseEntity<String> result;

        try {
            serviceService.updateServiceData(serviceId, servicesData);
            result = new ResponseEntity<>(serviceDataUpdated, HttpStatus.OK);
        } catch (ExceptionInInitializerError e){
            result = new ResponseEntity<>(
                    serviceDataNotUpdated + e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (ServiceException e){
            result = new ResponseEntity<>(
                    serviceDataNotUpdated + e.getMessage(), HttpStatus.CONFLICT);
        } catch (Exception e){
            result = new ResponseEntity<>(badRequest, HttpStatus.BAD_REQUEST);
        }
        return result;
    }

    @ApiOperation(
            value = "Delete Service Data",
            notes = "This Request Delete Service Data in the Database",
            tags = {"Services"})
    @ApiResponses(
            value = {
                    @ApiResponse(
                            code = 200,
                            message = "Delete Service Data",
                            response = ServicesDto.class),
                    @ApiResponse(code = 400, message = "Bad Request"),
                    @ApiResponse(code = 401, message = "Unauthorized Access"),
                    @ApiResponse(code = 404, message = "Service Not Found")
            })
    @DeleteMapping(path = {"/services/{id}"})
    public ResponseEntity<String> deleteService(@PathVariable("id") UUID serviceId) {

        ResponseEntity<String> result;

        try {
            serviceService.deleteServiceData(serviceId);
            result = new ResponseEntity<>(serviceDataDeleted, HttpStatus.OK);
        } catch (ExceptionInInitializerError e) {
            result = new ResponseEntity<>(
                    serviceDataNotDeleted + e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            result = new ResponseEntity<>(badRequest, HttpStatus.BAD_REQUEST);
        }
        return result;
    }
}
