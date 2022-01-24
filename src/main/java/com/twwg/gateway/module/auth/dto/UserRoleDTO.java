package com.twwg.gateway.module.auth.dto;

import com.twwg.gateway.module.auth.entity.UserRole;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
* 用户角色中间表 DTO定义
* @author MyName
* @version 1.0
* @date 2022-01-24
 * Copyright © MyCompany
*/
@Getter @Setter @Accessors(chain = true)
public class UserRoleDTO extends UserRole {
    private static final long serialVersionUID = 5677683968984621706L;

}