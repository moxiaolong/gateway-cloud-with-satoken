package com.twwg.gateway.module.auth.service;


import com.twwg.gateway.base.BaseCustomService;
import com.twwg.gateway.module.auth.entity.Permission;

import java.util.List;

/**
* 权限相关Service
* @author MyName
* @version 1.0
* @date 2022-01-24
 * Copyright © MyCompany
*/
public interface PermissionService extends BaseCustomService<Permission> {
    /**
     * 获取权限路径 根据用户id
     *
     * @param userId 用户id
     * @return {@link List}<{@link Permission}>
     */
    List<String> getPermissionUriByUserId(Long userId);
}