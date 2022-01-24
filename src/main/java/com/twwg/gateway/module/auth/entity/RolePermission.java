package com.twwg.gateway.module.auth.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.example.demo.entity.BaseCustomEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Date;

/**
* 角色权限中间表 Entity定义
* @author MyName
* @version 1.0
* @date 2022-01-24
* Copyright © MyCompany
*/
@Getter @Setter @Accessors(chain = true)
@TableName("auth_role_permission")
public class RolePermission extends BaseCustomEntity {
    private static final long serialVersionUID = -6430407056331858285L;

    /**
    * 创建人 
    */
    @TableField(fill = FieldFill.INSERT)
    private Long createBy;

    /**
    * 更新时间 
    */
    @TableField(insertStrategy = FieldStrategy.NEVER, updateStrategy = FieldStrategy.NEVER)
    private Date updateTime;

    /**
    * 角色 
    */
    @TableField()
    private Long authRoleId;

    /**
    * 权限 
    */
    @TableField()
    private Long authPermissionId;


} 
