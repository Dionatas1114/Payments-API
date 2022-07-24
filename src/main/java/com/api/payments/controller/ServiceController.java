package com.api.payments.controller;

import com.api.payments.dto.ServicesDto;
import com.api.payments.services.ServiceService;
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
        } catch (RepositoryException e){
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
        } catch (RepositoryException e){
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
}
