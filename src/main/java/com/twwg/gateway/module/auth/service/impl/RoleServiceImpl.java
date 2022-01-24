package com.twwg.gateway.module.auth.service.impl;


import com.twwg.gateway.base.BaseCustomServiceImpl;
import com.twwg.gateway.module.auth.entity.Role;
import com.twwg.gateway.module.auth.mapper.RoleMapper;
import com.twwg.gateway.module.auth.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
* 角色相关Service实现
* @author MyName
* @version 1.0
* @date 2022-01-24
 * Copyright © MyCompany
*/
@Service
@Slf4j
public class RoleServiceImpl extends BaseCustomServiceImpl<RoleMapper, Role> implements RoleService {

}
