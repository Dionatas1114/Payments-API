package com.api.payments.repository;

import com.api.payments.entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;

public interface ProductRepository extends JpaRepository<Products, UUID> {

    Optional<List<Products>> findByItemName(String itemName);
    Optional<List<Products>> findByProductBrand(String productBrand);
    Optional<List<Products>> findByCaptionPacking(String captionPacking);

}
