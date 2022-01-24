package com.twwg.gateway.module.auth.dto;

import com.twwg.gateway.module.auth.entity.RolePermission;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
* 角色权限中间表 DTO定义
* @author MyName
* @version 1.0
* @date 2022-01-24
 * Copyright © MyCompany
*/
@Getter @Setter @Accessors(chain = true)
public class RolePermissionDTO extends RolePermission {
    private static final long serialVersionUID = 739833423108860249L;

}