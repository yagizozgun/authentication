package com.bidfood.bidFoodIntegration.auth.controller;

import com.bidfood.bidFoodIntegration.auth.model.AuthRequest;
import com.bidfood.bidFoodIntegration.auth.security.JwtService;
import com.bidfood.bidFoodIntegration.auth.security.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RequestMapping("/api")
@RestController
public class tokenController {

    @Autowired
    private JwtService jwtService;
    @Autowired
    private UserService userService;

    @PostMapping("/authenticate")
    public String getToken(@RequestBody AuthRequest userRequest) throws IllegalAccessException {
        if (Objects.equals(userRequest.getUserName(), "tokenal") && Objects.equals(userRequest.getPassword(), "1111"))
        {
            UserDetails userDetails = userService.loadUserByUsername(userRequest.getUserName());
            return jwtService.generateToken(userDetails);
        }
        else
        {
            throw new IllegalAccessException("Unauthorized user");
        }
    }

}
