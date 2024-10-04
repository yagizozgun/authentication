package com.bidfood.bidFoodIntegration.business;

import com.bidfood.bidFoodIntegration.model.dto.CreateProductResponse;
import com.bidfood.bidFoodIntegration.dataAccess.ProductRepository;
import com.bidfood.bidFoodIntegration.model.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductManager {

    @Autowired
    private ProductRepository productRepository;

    public CreateProductResponse getProductByProductCode(String productCode)
    {
        Product product = productRepository.findByProductCode(productCode);
        CreateProductResponse productResponse = new CreateProductResponse();

        productResponse.setProductCode(product.getProductCode());
        productResponse.setProductName(product.getProductName());
        productResponse.setOrderType(product.getOrderType());
        // add other values later

        return productResponse;
    }
}
