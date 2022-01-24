package com.twwg.gateway.module.auth.vo;

import com.diboot.core.binding.annotation.BindEntity;
import com.twwg.gateway.module.auth.entity.Permission;
import com.twwg.gateway.module.auth.entity.RolePermission;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 权限 DetailVO定义
 * @author MyName
 * @version 1.0
 * @date 2022-01-24
 * Copyright © MyCompany
 */
@Getter
@Setter
@Accessors(chain = true)
public class PermissionDetailVO extends Permission {

    private static final long serialVersionUID = -1332287073550749790L;

    // 绑定authRolePermission
    @BindEntity(entity = RolePermission.class, condition = "this.id=auth_permission_id")
    private RolePermission authRolePermission;
}
