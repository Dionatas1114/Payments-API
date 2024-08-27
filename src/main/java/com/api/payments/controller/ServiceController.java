package com.api.payments.controller;

import com.api.payments.dto.ServicesDto;
import com.api.payments.exception.GenericExceptionHandler;
import com.api.payments.services.ServiceService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.hibernate.service.spi.ServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static com.api.payments.messages.GenericMessages.badRequest;
import static com.api.payments.messages.GenericMessages.unauthorized;
import static com.api.payments.messages.ServiceMessages.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/private")
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
                    @ApiResponse(code = 400, message = badRequest),
                    @ApiResponse(code = 401, message = unauthorized),
                    @ApiResponse(code = 404, message = "No Services Registered")
            })
    @GetMapping(path = {"/services"})
    public ResponseEntity<?> findAllServices() {

        try {
            List<ServicesDto> allServices = serviceService.findAllServices();
            return ResponseEntity.ok(allServices);
        } catch (Exception e) {
            return GenericExceptionHandler.getException(e);
        }
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
                    @ApiResponse(code = 400, message = badRequest),
                    @ApiResponse(code = 401, message = unauthorized),
                    @ApiResponse(code = 404, message = "Service Not Found")
            })
    @GetMapping(path = {"/services/{id}"})
    public ResponseEntity<?> findServiceById(@PathVariable("id") UUID serviceId) {

        try {
            ServicesDto service = serviceService.findServiceById(serviceId);
            return ResponseEntity.ok(service);
        } catch (Exception e) {
            return GenericExceptionHandler.getException(e);
        }
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
                    @ApiResponse(code = 400, message = badRequest),
                    @ApiResponse(code = 401, message = unauthorized),
                    @ApiResponse(code = 409, message = "Conflict")
            })
    @PostMapping(path = {"/services"})
    public ResponseEntity<?> createService(@Validated @RequestBody ServicesDto servicesData) {

        try {
            serviceService.saveServiceData(servicesData);
            return ResponseEntity.status(HttpStatus.CREATED).body(serviceDataInserted);
        } catch (Exception e) {
            return GenericExceptionHandler.getException(e);
        }
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
                    @ApiResponse(code = 400, message = badRequest),
                    @ApiResponse(code = 401, message = unauthorized),
                    @ApiResponse(code = 404, message = "Service Not Found"),
                    @ApiResponse(code = 409, message = "Conflict")
            })
    @PutMapping(path = {"/services/{id}"})
    public ResponseEntity<?> updateService(
            @PathVariable("id") UUID serviceId,
            @Validated @RequestBody ServicesDto servicesData) {

        try {
            serviceService.updateServiceData(serviceId, servicesData);
            return ResponseEntity.ok(serviceDataUpdated);
        } catch (ExceptionInInitializerError e) {
            return new ResponseEntity<>(
                    serviceDataNotUpdated + e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (ServiceException e) {
            return new ResponseEntity<>(
                    serviceDataNotUpdated + e.getMessage(), HttpStatus.CONFLICT);
        } catch (Exception e) {
            return GenericExceptionHandler.getException(e);
        }
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
                    @ApiResponse(code = 400, message = badRequest),
                    @ApiResponse(code = 401, message = unauthorized),
                    @ApiResponse(code = 404, message = "Service Not Found")
            })
    @DeleteMapping(path = {"/services/{id}"})
    public ResponseEntity<?> deleteService(@PathVariable("id") UUID serviceId) {

        try {
            serviceService.deleteServiceData(serviceId);
            return ResponseEntity.ok(serviceDataDeleted);
        } catch (Exception e) {
            return GenericExceptionHandler.getException(e);
        }
    }
}
