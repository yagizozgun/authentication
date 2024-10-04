package com.bidfood.bidFoodIntegration.webApi.controllers;

import com.bidfood.bidFoodIntegration.business.OutletManager;
import com.bidfood.bidFoodIntegration.model.dto.CreateOutletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class OutletsController {

    @Autowired
    private OutletManager outletManager;

    @GetMapping("/outlet")
    public CreateOutletResponse getOutletByOutletCode(@RequestParam String outletCode)
    {
        return outletManager.getOutletByOutletCode(outletCode);
    }

}
