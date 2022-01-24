package com.twwg.gateway.module.auth.vo;

import com.twwg.gateway.module.auth.entity.Permission;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
* 权限 ListVO定义
* @author MyName
* @version 1.0
* @date 2022-01-24
 * Copyright © MyCompany
*/
@Getter @Setter @Accessors(chain = true)
public class PermissionListVO extends Permission {
    private static final long serialVersionUID = 4102197926428366823L;

}