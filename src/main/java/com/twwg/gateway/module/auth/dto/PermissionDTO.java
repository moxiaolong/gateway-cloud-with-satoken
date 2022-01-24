package com.twwg.gateway.module.auth.dto;

import com.twwg.gateway.module.auth.entity.Permission;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
* 权限 DTO定义
* @author MyName
* @version 1.0
* @date 2022-01-24
 * Copyright © MyCompany
*/
@Getter @Setter @Accessors(chain = true)
public class PermissionDTO extends Permission {
    private static final long serialVersionUID = 807765613359730313L;

}