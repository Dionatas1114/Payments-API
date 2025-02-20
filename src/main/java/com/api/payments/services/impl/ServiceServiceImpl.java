package com.api.payments.services.impl;

import com.api.payments.dto.ServicesDto;
import com.api.payments.entity.Services;
import com.api.payments.repository.ServiceRepository;
import com.api.payments.services.ServiceService;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.api.payments.messages.ServiceMessages.*;
import static com.api.payments.validations.validators.ServiceValidator.serviceValidator;

@Service
@AllArgsConstructor
public class ServiceServiceImpl implements ServiceService {

    private ServiceRepository serviceRepository;

    @Override
    public List<ServicesDto> findAllServices() throws Exception {

        List<ServicesDto> servicesDtoList = new ArrayList<>();

        Optional.of(serviceRepository.findAll())
                .filter(services -> !services.isEmpty())
                .orElseThrow(() -> new NotFoundException(noServiceDataRegistered))
                .forEach(service -> servicesDtoList.add(convertToDto(service)));

        return servicesDtoList;
    }

    @Override
    public ServicesDto findServiceById(UUID serviceId) throws Exception {

        Services service = serviceRepository.findById(serviceId).orElseThrow(() -> new NotFoundException(serviceDataNotFound));
        return convertToDto(service);
    }

    @Override
    public void saveServiceData(ServicesDto servicesData) {

        serviceValidator(servicesData);
        serviceRepository.save(convertFromDto(servicesData));
    }

    @Override
    public void updateServiceData(UUID serviceId, ServicesDto servicesData) throws Exception {

        serviceRepository.findById(serviceId).orElseThrow(() -> new NotFoundException(serviceDataNotFound));

        serviceValidator(servicesData);

        servicesData.setId(serviceId);
        saveServiceData(servicesData);
    }

    @Override
    public void deleteServiceData(UUID serviceId) throws Exception {

        serviceRepository.findById(serviceId).orElseThrow(() -> new NotFoundException(serviceDataNotFound));
        serviceRepository.deleteById(serviceId);
    }

    private ServicesDto convertToDto(Services services) {
        return new ModelMapper().map(services, ServicesDto.class);
    }

    private Services convertFromDto(ServicesDto services) {
        return new ModelMapper().map(services, Services.class);
    }
}
