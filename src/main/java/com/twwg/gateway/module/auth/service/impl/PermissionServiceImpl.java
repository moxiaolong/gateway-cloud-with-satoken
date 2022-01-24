package com.twwg.gateway.module.auth.service.impl;

import com.twwg.gateway.base.BaseCustomServiceImpl;
import com.twwg.gateway.module.auth.entity.Permission;
import com.twwg.gateway.module.auth.mapper.PermissionMapper;
import com.twwg.gateway.module.auth.service.PermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


/**
* 权限相关Service实现
* @author MyName
* @version 1.0
* @date 2022-01-24
 * Copyright © MyCompany
*/
@Service
@Slf4j
public class PermissionServiceImpl extends BaseCustomServiceImpl<PermissionMapper, Permission> implements PermissionService {

}
