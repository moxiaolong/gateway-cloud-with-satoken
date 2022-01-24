package com.twwg.gateway.module.auth.mapper;


import com.diboot.core.mapper.BaseCrudMapper;
import com.twwg.gateway.module.auth.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
* 账户Mapper
* @author MyName
* @version 1.0
* @date 2022-01-24
 * Copyright © MyCompany
*/
@Mapper
public interface UserMapper extends BaseCrudMapper<User> {

}

