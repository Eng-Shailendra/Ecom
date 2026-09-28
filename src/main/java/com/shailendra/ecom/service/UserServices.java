package com.shailendra.ecom.service;

import com.shailendra.ecom.io.LoginRequest;
import com.shailendra.ecom.io.LoginResponse;
import com.shailendra.ecom.io.RegisterRequest;
import com.shailendra.ecom.io.RegisterResponse;


public interface UserServices {

    RegisterResponse register(RegisterRequest registerRequest);
    LoginResponse login(LoginRequest userRequest);







}
