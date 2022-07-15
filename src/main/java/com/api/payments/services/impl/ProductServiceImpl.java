package com.api.payments.services.impl;

import com.api.payments.dto.ProductsDto;
import com.api.payments.entity.Products;
import com.api.payments.repository.ProductRepository;
import com.api.payments.services.ProductService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.sonatype.aether.RepositoryException;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.api.payments.messages.ProductMessages.*;
import static com.api.payments.validations.validators.ProductValidator.*;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;

    @Override
    public List<ProductsDto> findAllProducts() throws Exception {

        List<Products> productsList = productRepository.findAll();
        List<ProductsDto> productsDtoList = new ArrayList<>();

        if (productsList.isEmpty())
            throw new RepositoryException(noProductDataRegistered);

        productsList.forEach(products -> productsDtoList.add(convertToDto(products)));

        return productsDtoList;
    }

    @Override
    public ProductsDto findProductById(UUID productId) throws RepositoryException {
        Optional<Products> product = productRepository.findById(productId);

        if (product.isEmpty())
            throw new RepositoryException(productDataNotFound);

        return convertToDto(product.get());
    }

    @Override
    public List<ProductsDto> findByItemName(String itemName) throws Exception {

        itemNameValidator(itemName);

        List<ProductsDto> productDtoList = new ArrayList<>();
        List<Products> productsByItemName = productRepository.findByItemName(itemName);

        boolean productsListEmpty = productsByItemName.isEmpty();
        if (productsListEmpty)
            throw new RepositoryException(productDataNotFound);

        for (Products product : productsByItemName)
            productDtoList.add(convertToDto(product));

        return productDtoList;
    }

    @Override
    public List<ProductsDto> findProductsByItemType(String productType) throws Exception {

        itemTypeValidator(productType);

        List<ProductsDto> productsDtoList = new ArrayList<>();
        List<Products> productsList = productRepository.findByItemType(productType);

        boolean productsListEmpty = productsList.isEmpty();
        if (productsListEmpty)
            throw new RepositoryException(noProductDataRegistered);

        for (Products products : productsList)
            productsDtoList.add(convertToDto(products));

        return productsDtoList;
    }

    public void saveProductData(ProductsDto productsData) throws Exception {

        String itemName = productsData.getItemName();
        String itemType = productsData.getItemType();
        String productBrand = productsData.getProductBrand();
        String captionPacking = productsData.getCaptionPacking();

        ArrayList<Object> booleanList = new ArrayList<>();
        List<Products> productsByItemName = productRepository.findByItemName(itemName);

        for (Products products : productsByItemName) {
            boolean alreadyExists = Objects.equals(products.itemType, itemType)
                    && Objects.equals(products.productBrand, productBrand)
                    && Objects.equals(products.captionPacking, captionPacking);

            booleanList.add(alreadyExists);
        }

        boolean alreadyExists = booleanList.contains(true);
        if (alreadyExists)
            throw new RepositoryException(productDataAlreadyExists);

        productValidator(productsData);

        Products products = convertFromDto(productsData);

        productRepository.save(products);
    }

    @Override
    public void updateProductData(UUID productId, ProductsDto productsData) throws Exception {

        boolean existsById = productRepository.existsById(productId);
        if (!existsById)
            throw new RepositoryException(productDataNotFound);

        productValidator(productsData);

        Products products = convertFromDto(productsData);

        products.setId(productId);
        productRepository.save(products);
    }

    @Override
    public void deleteProductData(UUID productId) throws Exception {

        boolean existsById = productRepository.existsById(productId);
        if (!existsById)
            throw new RepositoryException(productDataNotFound);

        productRepository.deleteById(productId);
    }

    private ProductsDto convertToDto(Products products) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(products, ProductsDto.class);
    }

    private Products convertFromDto(ProductsDto productsData) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(productsData, Products.class);
    }
}