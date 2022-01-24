package com.twwg.gateway.module.auth.service;


import com.twwg.gateway.base.BaseCustomService;
import com.twwg.gateway.module.auth.entity.UserRole;

import java.util.List;

/**
* 用户角色中间表相关Service
* @author MyName
* @version 1.0
* @date 2022-01-24
 * Copyright © MyCompany
*/
public interface UserRoleService extends BaseCustomService<UserRole> {

    /**
     * 角色id列表 by 用户id
     *
     * @param userId 用户角色列表
     * @return {@link List}<{@link Long}>
     */
    List<Long> getRoleIdsByUserId(Long userId);
}