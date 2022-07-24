package com.api.payments.services.impl;

import com.api.payments.dto.ServicesDto;
import com.api.payments.entity.Services;
import com.api.payments.repository.ServiceRepository;
import com.api.payments.services.ServiceService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.sonatype.aether.RepositoryException;
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

        List<ServicesDto> servicesList = new ArrayList<>();
        List<Services> allServicesList = serviceRepository.findAll();

        if (allServicesList.isEmpty())
            throw new RepositoryException(noServiceDataRegistered);

        allServicesList.forEach(service -> servicesList.add(convertToDto(service)));

        return servicesList;
    }

    @Override
    public ServicesDto findServiceById(UUID serviceId) throws RepositoryException {

        Optional<Services> service = serviceRepository.findById(serviceId);

        return convertToDto(service.get());
    }

    @Override
    public void saveServiceData(ServicesDto servicesData) {

        serviceValidator(servicesData);

        serviceRepository.save(convertFromDto(servicesData));
    }

    private ServicesDto convertToDto(Services services) {
        return new ModelMapper().map(services, ServicesDto.class);
    }

    private Services convertFromDto(ServicesDto services) {
        return new ModelMapper().map(services, Services.class);
    }
}
