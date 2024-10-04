package com.bidfood.bidFoodIntegration.dataAccess;

import com.bidfood.bidFoodIntegration.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
    Product findByProductCode(String productCode);
}