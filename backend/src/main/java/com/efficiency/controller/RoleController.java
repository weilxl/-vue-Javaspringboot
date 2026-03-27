package com.efficiency.controller;

import com.efficiency.common.ApiResponse;
import com.efficiency.entity.SysRole;
import com.efficiency.security.LoginUser;
import com.efficiency.security.UserContext;
import com.efficiency.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "角色管理")
@RestController
@RequestMapping("/role")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @ApiOperation("查询角色列表（仅管理员）")
    @GetMapping("/list")
    public ApiResponse<List<SysRole>> list() {
        LoginUser loginUser = UserContext.get();
        if (loginUser == null || !"ADMIN".equals(loginUser.getRoleCode())) {
            return ApiResponse.unauthorized("无权限访问角色管理");
        }
        return ApiResponse.success(roleService.listAll());
    }
}
