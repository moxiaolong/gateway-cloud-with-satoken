package com.twwg.gateway.module.auth.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.diboot.core.binding.QueryBuilder;
import com.diboot.core.vo.JsonResult;
import com.diboot.core.vo.Pagination;
import com.twwg.gateway.module.auth.entity.Role;
import com.twwg.gateway.module.auth.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;

/**
 * 角色管理
 *
 * @author dragon
 * @date 2022/01/24
 */
@RestController
@RequestMapping("/authManager/role")
public class RoleManagerController {
    @Autowired
    RoleService roleService;

    /**
     * 添加
     *
     * @param role 角色
     * @return {@link JsonResult}
     */
    @PostMapping
    public JsonResult add(@Valid @RequestBody Role role) {
        role.setCreateBy(StpUtil.getLoginIdAsLong());
        QueryWrapper<Role> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(Role::getRoleName, role.getRoleName());
        if (roleService.exists(wrapper)) {
            return JsonResult.FAIL_EXCEPTION("角色名已存在");
        }
        roleService.createEntity(role);
        return JsonResult.OK("添加成功");
    }

    /**
     * 删除
     *
     * @param roleId 角色id
     * @return {@link JsonResult}
     */
    @DeleteMapping("{roleId}")
    public JsonResult delete(@PathVariable("roleId") Long roleId) {
        Assert.isTrue(roleId != 10000003L, "初始角色禁止删除");
        return JsonResult.OK(roleService.deleteEntity(roleId));
    }

    /**
     * 更新
     *
     * @param role 角色
     * @return {@link JsonResult}
     */
    @PutMapping("")
    public JsonResult update(@Valid @RequestBody Role role) {
        role.setUpdateTime(new Date());

        QueryWrapper<Role> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(Role::getRoleName, role.getRoleName()).ne(Role::getId, role.getId());
        if (roleService.exists(wrapper)) {
            return JsonResult.FAIL_EXCEPTION("角色名已存在");
        }

        return JsonResult.OK(roleService.updateEntity(role));
    }

    /**
     * get
     *
     * @param roleId 角色id
     * @return {@link JsonResult}
     */
    @GetMapping("{roleId}")
    public JsonResult get(@PathVariable("roleId") Long roleId) {
        return JsonResult.OK(roleService.getEntity(roleId));
    }

    /**
     * 分页
     *
     * @param pagination 分页
     * @param role       角色
     * @return {@link JsonResult}
     */
    @GetMapping
    public JsonResult page(Role role, Pagination pagination) {
        QueryWrapper queryWrapper = QueryBuilder.toQueryWrapper(role);
        return JsonResult.OK(roleService.getViewObjectList(queryWrapper, pagination, Role.class)).bindPagination(pagination);
    }
}
