package com.twwg.gateway.module.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.twwg.gateway.base.BaseCustomServiceImpl;
import com.twwg.gateway.module.auth.entity.RolePermission;
import com.twwg.gateway.module.auth.mapper.RolePermissionMapper;
import com.twwg.gateway.module.auth.service.RolePermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


/**
* 角色权限中间表相关Service实现
* @author MyName
* @version 1.0
* @date 2022-01-24
 * Copyright © MyCompany
*/
@Service
@Slf4j
public class RolePermissionServiceImpl extends BaseCustomServiceImpl<RolePermissionMapper, RolePermission> implements RolePermissionService {

    @Override
    public List<Long> getPermissionIdsByRoleId(Long roleId) {
            QueryWrapper<RolePermission> userRoleQueryWrapper = new QueryWrapper<>();
            userRoleQueryWrapper.lambda().eq(RolePermission::getAuthRoleId, roleId).select(RolePermission::getAuthPermissionId);
            List<RolePermission> permissionList = this.getEntityList(userRoleQueryWrapper);
            return permissionList.stream().map(RolePermission::getAuthPermissionId).collect(Collectors.toList());
    }
}
