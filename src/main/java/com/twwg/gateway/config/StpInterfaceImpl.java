package com.twwg.gateway.config;

import cn.dev33.satoken.stp.StpInterface;
import com.twwg.gateway.module.auth.service.PermissionService;
import com.twwg.gateway.module.auth.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 自定义权限验证接口扩展
 *
 * @author dragon
 */
@Component
public class StpInterfaceImpl implements StpInterface {

    @Autowired
    PermissionService permissionService;
    @Autowired
    RoleService roleService;

    /**
     * 返回一个账号所拥有的权限码集合
     */
    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        if (loginId instanceof String) {
            loginId = Long.parseLong((String) loginId);
        }
        return permissionService.getPermissionUriByUserId((Long) loginId);
    }

    /**
     * 返回一个账号所拥有的角色标识集合 (权限与角色可分开校验)
     */
    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        if (loginId instanceof String) {
            loginId = Long.parseLong((String) loginId);
        }
        return roleService.getRoleNameListByUserId((Long) loginId);
    }

}