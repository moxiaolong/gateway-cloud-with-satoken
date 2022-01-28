package com.twwg.gateway.feign.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class UserDetail implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String username;
    private String realName;
    private String headUrl;
    private Long headId;
    private Integer gender;
    private String email;
    private String mobile;
    private String position;
    private Long deptId;
    private String password;
    private Integer status;
    private Integer superAdmin;
    private String userNo;
    /**
     * 部门数据权限
     */
    private List<Long> deptIdList;
    private List<Long> roleIdList;

    /**
     * 是否可以更新用户信息
     * */
    private Integer isUpdateUserInfo;

    /**
     * 是否可以修改密码
     * */
    private Integer isUpdatePassword;

}