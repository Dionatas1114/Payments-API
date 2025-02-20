package com.api.payments.services.impl;

import com.api.payments.dto.ProductsDto;
import com.api.payments.entity.Products;
import com.api.payments.repository.ProductRepository;
import com.api.payments.services.ProductService;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import static com.api.payments.messages.ProductMessages.*;
import static com.api.payments.validations.validators.ValidateField.validateField;
import static com.api.payments.validations.messages.ProductValidatorMessages.itemNameInvalid;
import static com.api.payments.validations.validators.ProductValidator.*;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;

    @Override
    public List<ProductsDto> findAllProducts() throws Exception {

        List<ProductsDto> productsDtoList = new ArrayList<>();

        Optional.of(productRepository.findAll())
                .filter(list -> !list.isEmpty())
                .orElseThrow(() -> new NotFoundException(noProductDataRegistered))
                .forEach(product -> productsDtoList.add(convertToDto(product)));

        return productsDtoList;
    }

    @Override
    public ProductsDto findProductById(UUID productId) throws Exception {

        Products product = productRepository
                .findById(productId)
                .orElseThrow(() -> new NotFoundException(productDataNotFound));

        return convertToDto(product);
    }

    @Override
    public List<ProductsDto> findByItemName(String itemName) throws Exception {

        List<ProductsDto> productDtoList = new ArrayList<>();
        validateField(itemName, itemNameInvalid);

        productRepository.findByItemName(itemName)
                .orElseThrow(() -> new NotFoundException(productDataNotFound))
                .forEach(product -> productDtoList.add(convertToDto(product)));

        return productDtoList;
    }

    public void saveProductData(ProductsDto productsData) throws Exception {

        String itemName = productsData.getItemName();
        String productBrand = productsData.getProductBrand();
        String captionPacking = productsData.getCaptionPacking();

        List<Products> productsByItemName = productRepository.findByItemName(itemName).orElse(new ArrayList<>());

        boolean alreadyExists = productsByItemName.stream().anyMatch(products ->
                Objects.equals(products.productBrand, productBrand)
                        && Objects.equals(products.captionPacking, captionPacking)
        );

        if (alreadyExists) throw new Exception(productDataAlreadyExists);

        productValidator(productsData);
        productRepository.save(convertFromDto(productsData));
    }

    @Override
    public void updateProductData(UUID productId, ProductsDto productsData) throws Exception {

        productRepository.findById(productId).orElseThrow(() -> new NotFoundException(productDataNotFound));

        productValidator(productsData);
        productsData.setId(productId);
        productRepository.save(convertFromDto(productsData));
    }

    @Override
    public void deleteProductData(UUID productId) throws Exception {

        productRepository.findById(productId).orElseThrow(() -> new NotFoundException(productDataNotFound));
        productRepository.deleteById(productId);
    }

    private ProductsDto convertToDto(Products products) {
        return new ModelMapper().map(products, ProductsDto.class);
    }

    private Products convertFromDto(ProductsDto productsData) {
        return new ModelMapper().map(productsData, Products.class);
    }
}