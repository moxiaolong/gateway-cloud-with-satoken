package com.twwg.gateway.module.auth.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.twwg.gateway.base.BaseCustomEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Date;

/**
* 权限 Entity定义
* @author MyName
* @version 1.0
* @date 2022-01-24
* Copyright © MyCompany
*/
@Getter @Setter @Accessors(chain = true)
@TableName("auth_permission")
public class Permission extends BaseCustomEntity {
    private static final long serialVersionUID = -7318062440938111077L;

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


} 
