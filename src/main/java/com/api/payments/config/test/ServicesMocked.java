package com.api.payments.config.test;

import com.api.payments.dto.ServicesDto;
import com.api.payments.entity.Services;
import com.api.payments.repository.ServiceRepository;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Configuration
@AllArgsConstructor
public class ServicesMocked {

    private static final UUID ID = UUID.fromString("4f9ab8ae-e62a-40f9-b7b8-66eb1d30b75a");
    private static final String ITEM_NAME = "Manutencao";
    private static final String ITEM_CATEGORY = "Informatica";
    private static final Double TOTAL_PRICE = 500.00;
    private static final Double DISCOUNT = 0.0;
    private static final String INTERNAL_CODE = "informatica_";

    private ServiceRepository serviceRepository;

    @Bean
    public ServicesDto returnServiceDtoMocked(){
        return ServicesDto.builder()
                .id(ID)
                .itemName(ITEM_NAME)
                .itemCategory(ITEM_CATEGORY)
                .totalPrice(TOTAL_PRICE)
                .discountPrice(DISCOUNT)
                .internalCode(INTERNAL_CODE)
                .build();
    }

    @Bean
    public Services returnServiceMocked(){

        val service = new Services();
        service.setItemName(ITEM_NAME);
        service.setItemCategory(ITEM_CATEGORY);
        service.setTotalPrice(TOTAL_PRICE);
        service.setDiscountPrice(DISCOUNT);
        service.setInternalCode(INTERNAL_CODE);

        return service;
    }

    @Bean
    public Optional<Services> returnOptionalServiceMocked() {
        return Optional.of(returnServiceMocked());
    }

    public void servicesDB() {

        val s1 = new Services();
        s1.setItemName(ITEM_NAME);
        s1.setItemCategory(ITEM_CATEGORY);
        s1.setTotalPrice(TOTAL_PRICE);
        s1.setDiscountPrice(DISCOUNT);
        s1.setInternalCode(INTERNAL_CODE);

        val s2 = new Services();
        s2.setItemName(ITEM_NAME);
        s2.setItemCategory(ITEM_CATEGORY);
        s2.setTotalPrice(TOTAL_PRICE);
        s2.setDiscountPrice(DISCOUNT);
        s2.setInternalCode(INTERNAL_CODE);

        serviceRepository.saveAll(List.of(s1, s2));
    }
}
