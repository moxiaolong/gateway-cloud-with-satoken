package com.twwg.gateway.module.auth.mapper;


import com.diboot.core.mapper.BaseCrudMapper;
import com.twwg.gateway.module.auth.entity.UserRole;
import org.apache.ibatis.annotations.Mapper;

/**
* 用户角色中间表Mapper
* @author MyName
* @version 1.0
* @date 2022-01-24
 * Copyright © MyCompany
*/
@Mapper
public interface UserRoleMapper extends BaseCrudMapper<UserRole> {

}

