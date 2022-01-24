package com.twwg.gateway.module.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.twwg.gateway.base.BaseCustomServiceImpl;
import com.twwg.gateway.module.auth.entity.Permission;
import com.twwg.gateway.module.auth.entity.RolePermission;
import com.twwg.gateway.module.auth.mapper.PermissionMapper;
import com.twwg.gateway.module.auth.service.PermissionService;
import com.twwg.gateway.module.auth.service.RolePermissionService;
import com.twwg.gateway.module.auth.service.RoleService;
import com.twwg.gateway.module.auth.service.UserRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


/**
 * 权限相关Service实现
 *
 * @author MyName
 * @version 1.0
 * @date 2022-01-24
 * Copyright © MyCompany
 */
@Service
@Slf4j
public class PermissionServiceImpl extends BaseCustomServiceImpl<PermissionMapper, Permission> implements PermissionService {

    @Autowired
    RoleService roleService;

    @Autowired
    UserRoleService userRoleService;

    @Autowired
    RolePermissionService rolePermissionService;


    @Override
    public List<String> getPermissionUriByUserId(Long userId) {
        List<Long> roleIdList =userRoleService.getRoleIdsByUserId(userId);

        //查询角色权限中间表
        QueryWrapper<RolePermission> rolePermissionQueryWrapper = new QueryWrapper<>();
        rolePermissionQueryWrapper.lambda().in(RolePermission::getAuthRoleId, roleIdList).select(RolePermission::getAuthPermissionId);
        List<RolePermission> rolePermissionList = rolePermissionService.getEntityList(rolePermissionQueryWrapper);
        List<Long> permissionIdList = rolePermissionList.stream().map(RolePermission::getAuthPermissionId).collect(Collectors.toList());

        //查询权限
        QueryWrapper<Permission> permissionQueryWrapper = new QueryWrapper<>();
        permissionQueryWrapper.lambda().in(Permission::getId, permissionIdList).select(Permission::getUri);
        List<Permission> permissionList = getEntityList(permissionQueryWrapper);
        return permissionList.stream().map(Permission::getUri).collect(Collectors.toList());
    }
}
