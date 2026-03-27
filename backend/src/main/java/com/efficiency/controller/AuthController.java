package com.efficiency.controller;

import com.efficiency.common.ApiResponse;
import com.efficiency.dto.LoginRequest;
import com.efficiency.dto.RegisterRequest;
import com.efficiency.service.AuthService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Api(tags = "认证接口")
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @ApiOperation("用户登录")
    @PostMapping("/login")
    public ApiResponse<Map<String, Object>> login(@Validated @RequestBody LoginRequest request) {
        return ApiResponse.success("登录成功", authService.login(request));
    }

    @ApiOperation("用户注册")
    @PostMapping("/register")
    public ApiResponse<Object> register(@Validated @RequestBody RegisterRequest request) {
        authService.register(request);
        return ApiResponse.success("注册成功", null);
    }
}
