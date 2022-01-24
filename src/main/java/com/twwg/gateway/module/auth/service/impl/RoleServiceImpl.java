package com.twwg.gateway.module.auth.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.twwg.gateway.base.BaseCustomServiceImpl;
import com.twwg.gateway.module.auth.entity.Role;
import com.twwg.gateway.module.auth.mapper.RoleMapper;
import com.twwg.gateway.module.auth.service.RoleService;
import com.twwg.gateway.module.auth.service.UserRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 角色相关Service实现
 *
 * @author MyName
 * @version 1.0
 * @date 2022-01-24
 * Copyright © MyCompany
 */
@Service
@Slf4j
public class RoleServiceImpl extends BaseCustomServiceImpl<RoleMapper, Role> implements RoleService {

    @Autowired
    UserRoleService userRoleService;

    @Override
    public List<String> getRoleNameListByUserId(Long userId) {
        List<Long> roleIds = userRoleService.getRoleIdsByUserId(userId);
        QueryWrapper<Role> roleQueryWrapper = new QueryWrapper<>();
        roleQueryWrapper.lambda().in(Role::getId, roleIds).select(Role::getRoleName);
        return this.getEntityList(roleQueryWrapper).stream().map(Role::getRoleName).collect(Collectors.toList());
    }
}
