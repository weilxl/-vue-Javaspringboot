package com.efficiency.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.efficiency.dto.LoginRequest;
import com.efficiency.dto.RegisterRequest;
import com.efficiency.entity.SysUser;
import com.efficiency.mapper.SysUserMapper;
import com.efficiency.security.JwtTokenUtil;
import com.efficiency.service.AuthService;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
public class AuthServiceImpl implements AuthService {

    private final SysUserMapper sysUserMapper;
    private final JwtTokenUtil jwtTokenUtil;

    public AuthServiceImpl(SysUserMapper sysUserMapper, JwtTokenUtil jwtTokenUtil) {
        this.sysUserMapper = sysUserMapper;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @Override
    public Map<String, Object> login(LoginRequest request) {
        SysUser user = sysUserMapper.selectOne(new LambdaQueryWrapper<SysUser>()
                .eq(SysUser::getUsername, request.getUsername()));
        if (user == null) {
            throw new RuntimeException("用户名不存在");
        }
        if (user.getStatus() != null && user.getStatus() != 1) {
            throw new RuntimeException("账号已停用");
        }
        String md5Password = md5(request.getPassword());
        if (!md5Password.equals(user.getPassword())) {
            throw new RuntimeException("密码错误");
        }
        String roleCode = StringUtils.hasText(user.getRoleCode()) ? user.getRoleCode() : "USER";
        //生成令牌
        String token = jwtTokenUtil.generateToken(user.getId(), user.getUsername(), roleCode);
        Map<String, Object> data = new HashMap<>();
        data.put("token", token);
        data.put("username", user.getUsername());
        data.put("realName", user.getRealName());
        data.put("roleCode", roleCode);
        return data;
    }

    @Override
    public void register(RegisterRequest request) {
        SysUser exists = sysUserMapper.selectOne(new LambdaQueryWrapper<SysUser>()
                .eq(SysUser::getUsername, request.getUsername()));
        if (exists != null) {
            throw new RuntimeException("用户名已存在");
        }
        SysUser user = new SysUser();
        user.setUsername(request.getUsername());
        user.setPassword(md5(request.getPassword()));
        user.setRealName(request.getRealName());
        user.setPhone(request.getPhone());
        user.setRoleCode("USER");
        user.setStatus(1);
        user.setCreateTime(LocalDateTime.now());
        sysUserMapper.insert(user);
    }

    private String md5(String source) {
        return DigestUtils.md5DigestAsHex(source.getBytes(StandardCharsets.UTF_8));
    }
}
