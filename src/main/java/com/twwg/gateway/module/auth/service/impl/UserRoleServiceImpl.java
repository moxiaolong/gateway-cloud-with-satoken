package com.twwg.gateway.module.auth.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.twwg.gateway.base.BaseCustomServiceImpl;
import com.twwg.gateway.module.auth.entity.UserRole;
import com.twwg.gateway.module.auth.mapper.UserRoleMapper;
import com.twwg.gateway.module.auth.service.UserRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 用户角色中间表相关Service实现
 *
 * @author MyName
 * @version 1.0
 * @date 2022-01-24
 * Copyright © MyCompany
 */
@Service
@Slf4j
public class UserRoleServiceImpl extends BaseCustomServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {

    @Override
    public List<Long> getRoleIdsByUserId(Long userId) {
        QueryWrapper<UserRole> userRoleQueryWrapper = new QueryWrapper<>();
        userRoleQueryWrapper.lambda().eq(UserRole::getAuthUserId, userId).select(UserRole::getAuthRoleId);
        List<UserRole> userRoleList = this.getEntityList(userRoleQueryWrapper);
        return userRoleList.stream().map(UserRole::getAuthRoleId).collect(Collectors.toList());
    }
}
