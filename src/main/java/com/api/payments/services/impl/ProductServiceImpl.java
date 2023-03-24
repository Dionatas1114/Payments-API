package com.api.payments.services.impl;

import com.api.payments.dto.ProductsDto;
import com.api.payments.entity.Products;
import com.api.payments.repository.ProductRepository;
import com.api.payments.services.ProductService;
import lombok.AllArgsConstructor;
import lombok.val;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.api.payments.messages.ProductMessages.*;
import static com.api.payments.validations.validators.ProductValidator.*;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;

    @Override
    public List<ProductsDto> findAllProducts() {

        val productsList = productRepository.findAll();
        if (productsList.isEmpty()) throw new ExceptionInInitializerError(noProductDataRegistered);

        List<ProductsDto> productsDtoList = new ArrayList<>();
        productsList.forEach(products -> productsDtoList.add(convertToDto(products)));
        return productsDtoList;
    }

    @Override
    public ProductsDto findProductById(UUID productId) throws ExceptionInInitializerError {

        val product = productRepository.findById(productId);
        if (product.isEmpty()) throw new ExceptionInInitializerError(productDataNotFound);
        return convertToDto(product.get());
    }

    @Override
    public List<ProductsDto> findByItemName(String itemName) {

        itemNameValidator(itemName);

        val productsByItemName = productRepository.findByItemName(itemName);

        boolean productsListEmpty = productsByItemName.isEmpty();
        if (productsListEmpty) throw new ExceptionInInitializerError(productDataNotFound);

        List<ProductsDto> productDtoList = new ArrayList<>();
        productsByItemName.forEach(product -> productDtoList.add(convertToDto(product)));
        return productDtoList;
    }

    public void saveProductData(ProductsDto productsData) {

        String itemName = productsData.getItemName();
        String productBrand = productsData.getProductBrand();
        String captionPacking = productsData.getCaptionPacking();

        ArrayList<Object> booleanList = new ArrayList<>();
        val productsByItemName = productRepository.findByItemName(itemName);

        productsByItemName.forEach(products -> {
            booleanList.add(
                    Objects.equals(products.productBrand, productBrand)
                            && Objects.equals(products.captionPacking, captionPacking));
        });

        boolean alreadyExists = booleanList.contains(true);
        if (alreadyExists) throw new ExceptionInInitializerError(productDataAlreadyExists);

        productValidator(productsData);
        val products = convertFromDto(productsData);
        productRepository.save(products);
    }

    @Override
    public void updateProductData(UUID productId, ProductsDto productsData) {

        boolean existsById = productRepository.existsById(productId);
        if (!existsById) throw new ExceptionInInitializerError(productDataNotFound);

        productValidator(productsData);
        val products = convertFromDto(productsData);
        products.setId(productId);
        productRepository.save(products);
    }

    @Override
    public void deleteProductData(UUID productId) {

        boolean existsById = productRepository.existsById(productId);
        if (!existsById) throw new ExceptionInInitializerError(productDataNotFound);
        productRepository.deleteById(productId);
    }

    private ProductsDto convertToDto(Products products) {
        return new ModelMapper().map(products, ProductsDto.class);
    }

    private Products convertFromDto(ProductsDto productsData) {
        return new ModelMapper().map(productsData, Products.class);
    }
}