package com.efficiency.controller;

import com.efficiency.common.ApiResponse;
import com.efficiency.entity.SysUser;
import com.efficiency.security.LoginUser;
import com.efficiency.security.UserContext;
import com.efficiency.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Api(tags = "用户接口")
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ApiOperation("获取当前登录用户信息")
    @GetMapping("/info")
    public ApiResponse<Map<String, Object>> userInfo() {
        LoginUser loginUser = UserContext.get();
        SysUser user = userService.getById(loginUser.getUserId());
        Map<String, Object> data = new HashMap<>();
        data.put("id", user.getId());
        data.put("username", user.getUsername());
        data.put("realName", user.getRealName());
        data.put("phone", user.getPhone());
        data.put("roleCode", user.getRoleCode());
        return ApiResponse.success(data);
    }
}
