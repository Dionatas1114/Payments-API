package com.api.payments.config.test;

import com.api.payments.dto.ProductsDto;
import com.api.payments.entity.Products;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Optional;
import java.util.UUID;

@Configuration
public class ProductsMocked {

    private static final UUID ID = UUID.fromString("4f9ab8ae-e62a-40f9-b7b8-66eb1d30b75a");
    private static final String ITEM_NAME = "Computador";
    private static final String ITEM_CATEGORY = "Informatica";
    private static final Double UNITARY_PRICE = 2000.99;
    private static final Double TOTAL_PRICE = 2000.99;
    private static final Double DISCOUNT = 0.0;
    private static final String BAR_CODE = "9781234567897";
    private static final String INTERNAL_CODE = "informatica_";
    private static final String PRODUCT_BRAND = "DELL";

    @Bean
    public ProductsDto returnProductDtoMocked(){

        return ProductsDto.builder()
                .itemName(ITEM_NAME)
                .itemCategory(ITEM_CATEGORY)
                .unitaryPrice(UNITARY_PRICE)
                .totalPrice(TOTAL_PRICE)
                .discountPrice(DISCOUNT)
                .barCode(BAR_CODE)
                .internalCode(INTERNAL_CODE)
                .productBrand(PRODUCT_BRAND)
                .build();
    }

    @Bean
    public Products returnProductMocked(){

        var product = new Products();
        product.setItemName(ITEM_NAME);
        product.setItemCategory(ITEM_CATEGORY);
        product.setUnitaryPrice(UNITARY_PRICE);
        product.setTotalPrice(TOTAL_PRICE);
        product.setDiscountPrice(DISCOUNT);
        product.setBarCode(BAR_CODE);
        product.setInternalCode(INTERNAL_CODE);
        product.setProductBrand(PRODUCT_BRAND);

        return product;
    }

    @Bean
    public Optional<Products> returnOptionalProductMocked() {
        return Optional.of(returnProductMocked());
    }
}
