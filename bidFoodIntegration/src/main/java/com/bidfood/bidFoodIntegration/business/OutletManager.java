package com.bidfood.bidFoodIntegration.business;

import com.bidfood.bidFoodIntegration.model.dto.CreateOutletResponse;
import com.bidfood.bidFoodIntegration.dataAccess.OutletRepository;
import com.bidfood.bidFoodIntegration.model.entity.Outlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OutletManager {

    @Autowired
    private OutletRepository outletRepository;

    public CreateOutletResponse getOutletByOutletCode(String outletCode)
    {
        Outlet outlet = outletRepository.findByOutletCode(outletCode);
        CreateOutletResponse createOutletResponse = new CreateOutletResponse();

        createOutletResponse.setOutletCode(outlet.getOutletCode());
        createOutletResponse.setSignName(outlet.getSignName());
        createOutletResponse.setLegalName(outlet.getLegalName());
        createOutletResponse.setGsmNumber(outlet.getGsmNumber());
        // add other values later

        return createOutletResponse;
    }
}
