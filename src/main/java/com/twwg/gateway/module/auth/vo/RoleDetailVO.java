package com.twwg.gateway.module.auth.vo;

import com.diboot.core.binding.annotation.BindEntity;
import com.twwg.gateway.module.auth.entity.Role;
import com.twwg.gateway.module.auth.entity.RolePermission;
import com.twwg.gateway.module.auth.entity.UserRole;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 角色 DetailVO定义
 * @author MyName
 * @version 1.0
 * @date 2022-01-24
 * Copyright © MyCompany
 */
@Getter
@Setter
@Accessors(chain = true)
public class RoleDetailVO extends Role {

    private static final long serialVersionUID = 8374972028762461513L;

    // 绑定authRolePermission
    @BindEntity(entity = RolePermission.class, condition = "this.id=auth_role_id")
    private RolePermission authRolePermission;

    // 绑定authUserRole
    @BindEntity(entity = UserRole.class, condition = "this.id=auth_role_id")
    private UserRole authUserRole;
}
