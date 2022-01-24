package com.twwg.gateway.module.auth.vo;

import com.twwg.gateway.module.auth.entity.Role;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
* 角色 ListVO定义
* @author MyName
* @version 1.0
* @date 2022-01-24
 * Copyright © MyCompany
*/
@Getter @Setter @Accessors(chain = true)
public class RoleListVO extends Role {
    private static final long serialVersionUID = -1294917614012638725L;

}