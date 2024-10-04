package com.bidfood.bidFoodIntegration.dataAccess;

import com.bidfood.bidFoodIntegration.model.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID> {
    List<Order> findByOrderCode(String orderCode);
}
