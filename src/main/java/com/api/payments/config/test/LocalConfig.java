package com.api.payments.config.test;

import com.api.payments.entity.Products;
import com.api.payments.entity.Users;
import com.api.payments.repository.ProductRepository;
import com.api.payments.repository.UserConfigurationsRepository;
import com.api.payments.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.List;

@Configuration
@AllArgsConstructor
@Profile("local")
public class LocalConfig {

    private UserRepository userRepository;
    private UserConfigurationsRepository userConfigurationsRepository;
    private ProductRepository productRepository;

    private UsersMocked usersMocked;
    private ProductsMocked productsMocked;

    @Bean
    public void startDB() {
        usersDB();
        productsDB();
    }

    public void usersDB() {
        val userMocked = usersMocked.returnUserMocked();
        Users u1 = new Users(userMocked.name, userMocked.email, userMocked.password, userMocked.phone, userMocked.userConfigurations);
        Users u2 = new Users(userMocked.name, userMocked.email, userMocked.password, userMocked.phone, userMocked.userConfigurations);
        userRepository.saveAll(List.of(u1, u2));
    }

    public void productsDB() {
        Products product = productsMocked.returnProductMocked();

        Products p1 = new Products();
        p1.setItemName(product.itemName);
        p1.setItemCategory(product.itemCategory);
        p1.setUnitaryPrice(product.unitaryPrice);
        p1.setTotalPrice(product.totalPrice);
        p1.setDiscountPrice(product.discountPrice);
        p1.setBarCode(product.barCode);
        p1.setInternalCode(product.internalCode);
        p1.setProductBrand(product.productBrand);

        Products p2 = new Products();
        p2.setItemName(product.itemName);
        p2.setItemCategory(product.itemCategory);
        p2.setUnitaryPrice(product.unitaryPrice);
        p2.setTotalPrice(product.totalPrice);
        p2.setDiscountPrice(product.discountPrice);
        p2.setBarCode(product.barCode);
        p2.setInternalCode(product.internalCode);
        p2.setProductBrand(product.productBrand);

        productRepository.saveAll(List.of(p1, p2));
    }
}
