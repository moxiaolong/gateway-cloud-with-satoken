package com.twwg.gateway.module.auth.vo;

import com.diboot.core.binding.annotation.BindEntity;
import com.twwg.gateway.module.auth.entity.Role;
import com.twwg.gateway.module.auth.entity.User;
import com.twwg.gateway.module.auth.entity.UserRole;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
* 用户角色中间表 DetailVO定义
* @author MyName
* @version 1.0
* @date 2022-01-24
 * Copyright © MyCompany
*/
@Getter @Setter @Accessors(chain = true)
public class UserRoleDetailVO extends UserRole {
    private static final long serialVersionUID = 7890428185069905868L;

    /**
    * 关联对象 ：User
    */ 
    @BindEntity(entity = User.class, condition = "this.auth_user_id=id")
    private User authUser;
		
    /**
    * 关联对象 ：Role
    */ 
    @BindEntity(entity = Role.class, condition = "this.auth_role_id=id")
    private Role authRole;
		
}