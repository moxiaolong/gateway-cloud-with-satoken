package com.twwg.gateway.module.auth.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.diboot.core.vo.JsonResult;
import com.twwg.gateway.module.auth.entity.RolePermission;
import com.twwg.gateway.module.auth.service.RolePermissionService;
import com.twwg.gateway.module.auth.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 角色权限
 *
 * @author dragon
 * @date 2022/01/24
 */
@RestController
@RequestMapping("/authManager/rolePermission")
public class RolePermissionManagerController {
    @Autowired
    RolePermissionService rolePermissionService;
    @Autowired
    RoleService roleService;

    /**
     * 查询角色包含的权限ID列表
     *
     * @param roleId 角色id
     * @return {@link JsonResult}
     */
    @GetMapping("{roleId}")
    public JsonResult getUserRoleIds(@PathVariable("roleId") Long roleId) {
        return JsonResult.OK(rolePermissionService.getPermissionIdsByRoleId(roleId));
    }

    /**
     * 添加 角色 的权限
     *
     * @param roleId       角色id
     * @param permissionId 权限id
     * @return {@link JsonResult}
     */
    @PostMapping("{roleId}/{permissionId}")
    public JsonResult addUserRole(@PathVariable("roleId") Long roleId, @PathVariable("permissionId") Long permissionId) {
        QueryWrapper<RolePermission> userRoleQueryWrapper = new QueryWrapper<>();
        userRoleQueryWrapper.lambda().eq(RolePermission::getAuthRoleId, roleId).eq(RolePermission::getAuthPermissionId, permissionId);
        RolePermission rolePermission = rolePermissionService.getSingleEntity(userRoleQueryWrapper);
        if (rolePermission == null) {
            rolePermission = new RolePermission();
            rolePermission.setAuthRoleId(roleId);
            rolePermission.setAuthPermissionId(permissionId);
            rolePermission.setCreateBy(StpUtil.getLoginIdAsLong());
        }
        return JsonResult.OK(rolePermissionService.createOrUpdateEntity(rolePermission));
    }

    /**
     * 删除 角色 的权限
     *
     * @param roleId       角色id
     * @param permissionId 权限id
     * @return {@link JsonResult}
     */
    @DeleteMapping("{roleId}/{permissionId}")
    public JsonResult deleteUserRole(@PathVariable("roleId") Long roleId, @PathVariable("permissionId") Long permissionId) {
        QueryWrapper<RolePermission> userRoleQueryWrapper = new QueryWrapper<>();
        userRoleQueryWrapper.lambda().eq(RolePermission::getAuthRoleId, roleId).eq(RolePermission::getAuthPermissionId, permissionId);
        return JsonResult.OK(rolePermissionService.deleteEntities(userRoleQueryWrapper));
    }
}
