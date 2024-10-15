package com.api.payments.services;

import com.api.payments.dto.ServicesDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface ServiceService {

    List<ServicesDto> findAllServices() throws Exception;
    ServicesDto findServiceById(UUID serviceId) throws Exception;

    void saveServiceData(ServicesDto servicesData) throws Exception;
    void updateServiceData(UUID serviceId, ServicesDto servicesData) throws ExceptionInInitializerError, Exception;
    void deleteServiceData(UUID serviceId) throws ExceptionInInitializerError, Exception;
}
