package com.twwg.gateway.module.auth.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.twwg.gateway.base.BaseCustomEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import java.util.Date;

/**
* 角色 Entity定义
* @author MyName
* @version 1.0
* @date 2022-01-24
* Copyright © MyCompany
*/
@Getter @Setter @Accessors(chain = true)
@TableName("auth_role")
public class Role extends BaseCustomEntity {
    private static final long serialVersionUID = -594871399346124826L;

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
     * 角色名
     */
    @Length(max = 100, message = "角色名长度应小于100")
    @TableField()
    private String roleName;

    /**
     * 描述
     */
    @Length(max = 100, message = "描述长度应小于100")
    @TableField()
    private String description;

} 
