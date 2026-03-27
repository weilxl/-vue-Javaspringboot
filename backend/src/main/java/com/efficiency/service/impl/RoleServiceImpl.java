package com.efficiency.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.efficiency.entity.SysRole;
import com.efficiency.mapper.SysRoleMapper;
import com.efficiency.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    private final SysRoleMapper sysRoleMapper;

    public RoleServiceImpl(SysRoleMapper sysRoleMapper) {
        this.sysRoleMapper = sysRoleMapper;
    }

    @Override
    public List<SysRole> listAll() {
        return sysRoleMapper.selectList(new LambdaQueryWrapper<SysRole>().orderByAsc(SysRole::getId));
    }
}
