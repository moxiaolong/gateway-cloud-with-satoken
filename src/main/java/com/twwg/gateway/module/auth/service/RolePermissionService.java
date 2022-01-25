package com.twwg.gateway.module.auth.service;


import com.twwg.gateway.base.BaseCustomService;
import com.twwg.gateway.module.auth.entity.RolePermission;

import java.util.List;

/**
* 角色权限中间表相关Service
* @author MyName
* @version 1.0
* @date 2022-01-24
 * Copyright © MyCompany
*/
public interface RolePermissionService extends BaseCustomService<RolePermission> {
    /**
     * get 许可ids by 角色id
     *
     * @param roleId 角色id
     * @return {@link List}<{@link Long}>
     */
    List<Long> getPermissionIdsByRoleId(Long roleId);
}