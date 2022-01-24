package com.twwg.gateway.module.auth.dto;

import com.twwg.gateway.module.auth.entity.Role;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
* 角色 DTO定义
* @author MyName
* @version 1.0
* @date 2022-01-24
 * Copyright © MyCompany
*/
@Getter @Setter @Accessors(chain = true)
public class RoleDTO extends Role {
    private static final long serialVersionUID = -1190051391323291102L;

}