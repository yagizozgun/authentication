package com.bidfood.bidFoodIntegration.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @Column(name = "uid")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uid;

    @Column(name = "order_code")
    private String orderCode;

    @Column(name = "outlet_uid")
    private UUID outletUid;

    @Column(name = "product_uid")
    private UUID productUid;

    @Column(name = "cargo_width")
    private int cargoWidth;

    @Column(name = "cargo_depth")
    private int cargoDepth;

    @Column(name = "cargo_weight")
    private int cargoWeight;

    @Column(name = "cargo_height")
    private int cargoHeight;

    @Column(name = "delivery_date")
    private String deliveryDate;

    @Column(name = "order_date")
    private String orderDate;

}
