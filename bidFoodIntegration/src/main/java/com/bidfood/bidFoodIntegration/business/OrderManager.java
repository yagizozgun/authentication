package com.bidfood.bidFoodIntegration.business;

import com.bidfood.bidFoodIntegration.model.dto.CreateOrderResponse;
import com.bidfood.bidFoodIntegration.model.dto.CreateProductResponse;
import com.bidfood.bidFoodIntegration.dataAccess.OrderRepository;
import com.bidfood.bidFoodIntegration.dataAccess.ProductRepository;
import com.bidfood.bidFoodIntegration.model.entity.Order;
import com.bidfood.bidFoodIntegration.model.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class OrderManager {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ProductRepository productRepository;

    public CreateOrderResponse getOrderByOrderCode(String orderCode)
    {
        List<Order> orderList = orderRepository.findByOrderCode(orderCode);
        CreateOrderResponse orderResponse = new CreateOrderResponse();
        List<CreateProductResponse> productList = new ArrayList<>();
        for (Order order : orderList)
        {
            Product product = productRepository.findById(order.getProductUid()).orElseThrow();
            CreateProductResponse productResponse = new CreateProductResponse();
            productResponse.setProductName(product.getProductName());
            productResponse.setProductCode(product.getProductCode());
            // add other values later
            productList.add(productResponse);
        }

        Order order = orderList.get(0);
        orderResponse.setOrderCode(order.getOrderCode());
        orderResponse.setCargoWeight(order.getCargoWeight());
        orderResponse.setCargoDepth(order.getCargoDepth());
        orderResponse.setCargoHeight(order.getCargoHeight());
        orderResponse.setCargoWidth(order.getCargoWidth());
        orderResponse.setProducts(productList);

        return orderResponse;
    }

}
