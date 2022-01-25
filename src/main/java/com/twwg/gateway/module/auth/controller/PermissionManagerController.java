package com.twwg.gateway.module.auth.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.diboot.core.binding.QueryBuilder;
import com.diboot.core.vo.JsonResult;
import com.diboot.core.vo.Pagination;
import com.twwg.gateway.module.auth.entity.Permission;
import com.twwg.gateway.module.auth.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;

/**
 * 权限管理 用户管理
 *
 * @author dragon
 * @date 2022/01/24
 */
@RestController
@RequestMapping("/authManager/permission")
public class PermissionManagerController {
    @Autowired
    PermissionService permissionService;

    /**
     * 添加
     *
     * @param permission 权限
     * @return {@link JsonResult}
     */
    @PostMapping
    public JsonResult add(@Valid @RequestBody Permission permission) {
        Assert.isTrue(permission.getUri().contains(":/"), "请按照格式填写uri，例：POST:/addUser");
        permission.setCreateBy(StpUtil.getLoginIdAsLong());
        QueryWrapper<Permission> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(Permission::getUri, permission.getUri());
        if (permissionService.exists(wrapper)) {
            return JsonResult.FAIL_EXCEPTION("uri已存在");
        }
        permissionService.createEntity(permission);
        return JsonResult.OK("添加成功");
    }

    /**
     * 删除
     *
     * @param permissionId id
     * @return {@link JsonResult}
     */
    @DeleteMapping("{permissionId}")
    public JsonResult delete(@PathVariable("permissionId") Long permissionId) {

        return JsonResult.OK(permissionService.deleteEntity(permissionId));
    }

    /**
     * 更新
     *
     * @param permission 权限
     * @return {@link JsonResult}
     */
    @PutMapping("")
    public JsonResult update(@Valid @RequestBody Permission permission) {
        Assert.isTrue(permission.getUri().contains(":/"), "请按照格式填写uri，例：POST:/addUser");
        QueryWrapper<Permission> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(Permission::getUri, permission.getUri()).ne(Permission::getId, permission.getId());
        if (permissionService.exists(wrapper)) {
            return JsonResult.FAIL_EXCEPTION("uri已存在");
        }
        permission.setUpdateTime(new Date());
        return JsonResult.OK(permissionService.updateEntity(permission));
    }

    /**
     * get
     *
     * @param permissionId id
     * @return {@link JsonResult}
     */
    @GetMapping("{permissionId}")
    public JsonResult get(@PathVariable("permissionId") Long permissionId) {
        return JsonResult.OK(permissionService.getEntity(permissionId));
    }

    /**
     * 分页
     *
     * @param pagination 分页
     * @param permission 权限
     * @return {@link JsonResult}
     */
    @GetMapping
    public JsonResult page(Permission permission, Pagination pagination) {
        QueryWrapper queryWrapper = QueryBuilder.toQueryWrapper(permission);
        return JsonResult.OK(permissionService.getViewObjectList(queryWrapper, pagination, Permission.class)).bindPagination(pagination);
    }
}
