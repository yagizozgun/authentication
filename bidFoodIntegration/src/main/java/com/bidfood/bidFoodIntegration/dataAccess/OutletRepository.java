package com.bidfood.bidFoodIntegration.dataAccess;

import com.bidfood.bidFoodIntegration.model.entity.Outlet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OutletRepository extends JpaRepository<Outlet, UUID> {
    Outlet findByOutletCode(String outletCode);
}
