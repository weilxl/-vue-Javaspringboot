package com.efficiency.service;

import com.efficiency.dto.LoginRequest;
import com.efficiency.dto.RegisterRequest;

import java.util.Map;

public interface AuthService {
    Map<String, Object> login(LoginRequest request);
    void register(RegisterRequest request);
}
