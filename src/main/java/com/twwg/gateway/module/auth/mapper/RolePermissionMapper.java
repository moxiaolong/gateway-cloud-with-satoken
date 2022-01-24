package com.twwg.gateway.module.auth.mapper;


import com.diboot.core.mapper.BaseCrudMapper;
import com.twwg.gateway.module.auth.entity.RolePermission;
import org.apache.ibatis.annotations.Mapper;

/**
* 角色权限中间表Mapper
* @author MyName
* @version 1.0
* @date 2022-01-24
 * Copyright © MyCompany
*/
@Mapper
public interface RolePermissionMapper extends BaseCrudMapper<RolePermission> {

}

