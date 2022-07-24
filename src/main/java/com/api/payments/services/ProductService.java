package com.api.payments.services;

import com.api.payments.dto.ProductsDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface ProductService {

    List<ProductsDto> findAllProducts() throws Exception;
    ProductsDto findProductById(UUID productId) throws Exception;
    List<ProductsDto> findByItemName(String itemName) throws Exception;
    void saveProductData(ProductsDto productsData) throws Exception;
    void updateProductData(UUID productId, ProductsDto productsData) throws Exception;
    void deleteProductData(UUID productId) throws Exception;

}
