package com.api.payments.services.impl;

import com.api.payments.dto.ServicesDto;
import com.api.payments.entity.Services;
import com.api.payments.repository.ServiceRepository;
import com.api.payments.services.ServiceService;
import lombok.AllArgsConstructor;
import lombok.val;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.api.payments.messages.ServiceMessages.*;
import static com.api.payments.validations.validators.ServiceValidator.serviceValidator;

@Service
@AllArgsConstructor
public class ServiceServiceImpl implements ServiceService {

    private ServiceRepository serviceRepository;

    @Override
    public List<ServicesDto> findAllServices() {

        val allServicesList = serviceRepository.findAll();
        if (allServicesList.isEmpty()) throw new ExceptionInInitializerError(noServiceDataRegistered);

        List<ServicesDto> servicesList = new ArrayList<>();
        allServicesList.forEach(service -> servicesList.add(convertToDto(service)));
        return servicesList;
    }

    @Override
    public ServicesDto findServiceById(UUID serviceId) {

        val service = serviceRepository.findById(serviceId);
        if (service.isEmpty()) throw new ExceptionInInitializerError(serviceDataNotFound);
        return convertToDto(service.get());
    }

    @Override
    public void saveServiceData(ServicesDto servicesData) {

        serviceValidator(servicesData);
        serviceRepository.save(convertFromDto(servicesData));
    }

    @Override
    public void updateServiceData(UUID serviceId, ServicesDto servicesData) throws ExceptionInInitializerError {

        val serviceExists = serviceRepository.existsById(serviceId);
        if (!serviceExists) throw new ExceptionInInitializerError(serviceDataNotFound);

        serviceValidator(servicesData);
        val services = convertFromDto(servicesData);
        services.setId(serviceId);
        serviceRepository.save(services);
    }

    @Override
    public void deleteServiceData(UUID serviceId) throws ExceptionInInitializerError {

        val serviceExists = serviceRepository.existsById(serviceId);
        if (!serviceExists) throw new ExceptionInInitializerError(serviceDataNotFound);
        serviceRepository.deleteById(serviceId);
    }

    private ServicesDto convertToDto(Services services) {
        return new ModelMapper().map(services, ServicesDto.class);
    }

    private Services convertFromDto(ServicesDto services) {
        return new ModelMapper().map(services, Services.class);
    }
}
