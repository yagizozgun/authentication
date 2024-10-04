package com.bidfood.bidFoodIntegration.webApi.controllers;

import com.bidfood.bidFoodIntegration.model.dto.CreateOrderResponse;
import com.bidfood.bidFoodIntegration.business.OrderManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class OrdersController {

    @Autowired
    private OrderManager orderManager;

    @GetMapping("/order")
    public CreateOrderResponse getOrderByOrderCode(@RequestParam String orderCode)
    {
        return orderManager.getOrderByOrderCode(orderCode);
    }
}
