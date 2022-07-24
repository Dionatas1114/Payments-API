package com.api.payments.repository;

import com.api.payments.entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;

public interface ProductRepository extends JpaRepository<Products, UUID> {

    List<Products> findByItemName(String itemName);
    List<Products> findByProductBrand(String productBrand);
    List<Products> findByCaptionPacking(String captionPacking);

}
