package com.twwg.gateway.module.auth.service.impl;


import com.twwg.gateway.base.BaseCustomServiceImpl;
import com.twwg.gateway.module.auth.entity.User;
import com.twwg.gateway.module.auth.mapper.UserMapper;
import com.twwg.gateway.module.auth.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
* 账户相关Service实现
* @author MyName
* @version 1.0
* @date 2022-01-24
 * Copyright © MyCompany
*/
@Service
@Slf4j
public class UserServiceImpl extends BaseCustomServiceImpl<UserMapper, User> implements UserService {

}
