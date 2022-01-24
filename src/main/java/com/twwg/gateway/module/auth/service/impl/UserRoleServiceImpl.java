package com.twwg.gateway.module.auth.service.impl;


import com.twwg.gateway.base.BaseCustomServiceImpl;
import com.twwg.gateway.module.auth.entity.UserRole;
import com.twwg.gateway.module.auth.mapper.UserRoleMapper;
import com.twwg.gateway.module.auth.service.UserRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
* 用户角色中间表相关Service实现
* @author MyName
* @version 1.0
* @date 2022-01-24
 * Copyright © MyCompany
*/
@Service
@Slf4j
public class UserRoleServiceImpl extends BaseCustomServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {

}
