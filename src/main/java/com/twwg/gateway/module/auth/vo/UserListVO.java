package com.twwg.gateway.module.auth.vo;

import com.twwg.gateway.module.auth.entity.User;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
* 账户 ListVO定义
* @author MyName
* @version 1.0
* @date 2022-01-24
 * Copyright © MyCompany
*/
@Getter @Setter @Accessors(chain = true)
public class UserListVO extends User {
    private static final long serialVersionUID = -5671577664036285031L;

}