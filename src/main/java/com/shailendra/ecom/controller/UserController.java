package com.shailendra.ecom.controller;

import com.shailendra.ecom.io.LoginRequest;
import com.shailendra.ecom.io.LoginResponse;
import com.shailendra.ecom.io.RegisterRequest;
import com.shailendra.ecom.io.RegisterResponse;
import com.shailendra.ecom.service.UserServices;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public  class UserController{

    private final UserServices userServices;

    /**
     * User register route for the applicant
     * @param request api/v1/auth/register
     * @return --- id and email
     */

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterRequest request) {

        RegisterResponse user = userServices.register(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }


    @PostMapping("/login")
    public LoginResponse login(@Valid @RequestBody LoginRequest request){

        LoginResponse user = userServices.login(request);
        return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(user).getBody();

    }

}