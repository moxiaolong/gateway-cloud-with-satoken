package com.twwg.gateway.module.auth.service;


import com.twwg.gateway.base.BaseCustomService;
import com.twwg.gateway.module.auth.entity.Role;

import java.util.List;

/**
* 角色相关Service
* @author MyName
* @version 1.0
* @date 2022-01-24
 * Copyright © MyCompany
*/
public interface RoleService extends BaseCustomService<Role> {
    /**
     * 角色名称列表 by 用户id
     *
     * @param userId 用户id
     * @return {@link List}<{@link String}>
     */
    List<String> getRoleNameListByUserId(Long userId);
}