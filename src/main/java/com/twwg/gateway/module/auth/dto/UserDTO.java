package com.twwg.gateway.module.auth.dto;

import com.twwg.gateway.module.auth.entity.User;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
* 账户 DTO定义
* @author MyName
* @version 1.0
* @date 2022-01-24
 * Copyright © MyCompany
*/
@Getter @Setter @Accessors(chain = true)
public class UserDTO extends User {
    private static final long serialVersionUID = 7451719063499482895L;

}