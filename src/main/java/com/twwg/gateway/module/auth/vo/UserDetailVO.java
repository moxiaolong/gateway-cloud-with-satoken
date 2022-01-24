package com.twwg.gateway.module.auth.vo;

import com.diboot.core.binding.annotation.BindEntity;
import com.twwg.gateway.module.auth.entity.User;
import com.twwg.gateway.module.auth.entity.UserRole;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 账户 DetailVO定义
 * @author MyName
 * @version 1.0
 * @date 2022-01-24
 * Copyright © MyCompany
 */
@Getter
@Setter
@Accessors(chain = true)
public class UserDetailVO extends User {

    private static final long serialVersionUID = -162376230735502540L;

    // 绑定authUserRole
    @BindEntity(entity = UserRole.class, condition = "this.id=auth_user_id")
    private UserRole authUserRole;
}
