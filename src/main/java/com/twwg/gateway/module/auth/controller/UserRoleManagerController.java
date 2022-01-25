package com.twwg.gateway.module.auth.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.diboot.core.vo.JsonResult;
import com.twwg.gateway.module.auth.entity.UserRole;
import com.twwg.gateway.module.auth.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 用户角色管理
 *
 * @author dragon
 * @date 2022/01/24
 */
@RestController
@RequestMapping("/authManager/userRole")
public class UserRoleManagerController {

    @Autowired
    UserRoleService userRoleService;

    /**
     * 查询用户包含的角色ID列表
     *
     * @param userId 用户id
     * @return {@link JsonResult}
     */
    @GetMapping("{userId}")
    public JsonResult getUserRoleIds(@PathVariable("userId") Long userId) {
        return JsonResult.OK(userRoleService.getRoleIdsByUserId(userId));
    }

    /**
     * 为用户增加角色
     *
     * @param userId
     * @return
     */
    @PostMapping("{userId}/{roleId}")
    public JsonResult addUserRole(@PathVariable("userId") Long userId, @PathVariable("roleId") Long roleId) {
        QueryWrapper<UserRole> userRoleQueryWrapper = new QueryWrapper<>();
        userRoleQueryWrapper.lambda().eq(UserRole::getAuthUserId, userId).eq(UserRole::getAuthRoleId, roleId);
        UserRole userRole = userRoleService.getEntity(userRoleQueryWrapper);
        if (userRole == null) {
            userRole = new UserRole();
            userRole.setAuthUserId(userId);
            userRole.setAuthRoleId(roleId);
            userRole.setCreateBy(StpUtil.getLoginIdAsLong());
        }
        return JsonResult.OK(userRoleService.createOrUpdateEntity(userRole));
    }

    /**
     * 为用户删除角色
     *
     * @param userId 用户id
     * @return {@link JsonResult}
     */
    @DeleteMapping("{userId}/{roleId}")
    public JsonResult deleteUserRole(@PathVariable("userId") Long userId, @PathVariable("roleId") Long roleId) {
        QueryWrapper<UserRole> userRoleQueryWrapper = new QueryWrapper<>();
        userRoleQueryWrapper.lambda().eq(UserRole::getAuthUserId, userId).eq(UserRole::getAuthRoleId, roleId);
        return JsonResult.OK(userRoleService.deleteEntity(userRoleQueryWrapper));
    }
}
