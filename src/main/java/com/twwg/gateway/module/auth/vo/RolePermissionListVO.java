package com.twwg.gateway.module.auth.vo;

import com.diboot.core.binding.annotation.BindEntity;
import com.twwg.gateway.module.auth.entity.Permission;
import com.twwg.gateway.module.auth.entity.Role;
import com.twwg.gateway.module.auth.entity.RolePermission;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
* 角色权限中间表 ListVO定义
* @author MyName
* @version 1.0
* @date 2022-01-24
 * Copyright © MyCompany
*/
@Getter @Setter @Accessors(chain = true)
public class RolePermissionListVO extends RolePermission {
    private static final long serialVersionUID = 3938393782933890071L;

    /**
    * 关联对象 ：Role
    */
    @BindEntity(entity = Role.class, condition = "this.auth_role_id=id")
    private Role authRole;

    /**
    * 关联对象 ：Permission
    */
    @BindEntity(entity = Permission.class, condition = "this.auth_permission_id=id")
    private Permission authPermission;

}