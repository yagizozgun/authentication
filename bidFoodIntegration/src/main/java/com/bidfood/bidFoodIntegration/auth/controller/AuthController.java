package com.bidfood.bidFoodIntegration.auth.controller;

import com.bidfood.bidFoodIntegration.auth.service.AuthManager;
import com.bidfood.bidFoodIntegration.auth.model.AuthRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class AuthController {

    @Autowired
    private AuthManager authManager;

    @PostMapping("/authorize")
    public String getToken(@RequestBody AuthRequest authRequest)
    {
        return authManager.getToken(authRequest.getUserName(), authRequest.getPassword());
    }

    @GetMapping("/data")
    public String getData()
    {
        return authManager.getData();
    }

    @PostMapping("/update")
    public ResponseEntity<String> dataUpdate() throws JsonProcessingException {
        return authManager.saveData();
    }
}
