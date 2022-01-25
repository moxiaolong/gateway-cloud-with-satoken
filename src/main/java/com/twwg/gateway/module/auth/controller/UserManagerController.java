package com.twwg.gateway.module.auth.controller;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.crypto.digest.BCrypt;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.diboot.core.binding.QueryBuilder;
import com.diboot.core.vo.JsonResult;
import com.diboot.core.vo.Pagination;
import com.twwg.gateway.module.auth.entity.User;
import com.twwg.gateway.module.auth.service.UserService;
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
@RequestMapping("/authManager/user")
public class UserManagerController {
    @Autowired
    UserService userService;

    /**
     * 添加
     *
     * @param user 用户
     * @return {@link JsonResult}
     */
    @PostMapping
    public JsonResult add(@Valid @RequestBody User user) {
        user.setCreateBy(StpUtil.getLoginIdAsLong());
        user.setPasswd(BCrypt.hashpw(user.getPasswd()));

        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(User::getUserName, user.getUserName());
        if (userService.exists(wrapper)) {
            return JsonResult.FAIL_EXCEPTION("用户名已存在");
        }

        userService.createEntity(user);
        return JsonResult.OK("添加成功");
    }

    /**
     * 删除
     *
     * @param userId 用户id
     * @return {@link JsonResult}
     */
    @DeleteMapping("{userId}")
    public JsonResult delete(@PathVariable("userId") Long userId) {
        Assert.isTrue(userId != 10000003L, "初始用户禁止删除");
        return JsonResult.OK(userService.deleteEntity(userId));
    }

    /**
     * 更新
     *
     * @param user 用户
     * @return {@link JsonResult}
     */
    @PutMapping("")
    public JsonResult update(@Valid @RequestBody User user) {
        user.setPasswd(BCrypt.hashpw(user.getPasswd()));
        user.setUpdateTime(new Date());

        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(User::getUserName, user.getUserName()).ne(User::getId, user.getId());
        if (userService.exists(wrapper)) {
            return JsonResult.FAIL_EXCEPTION("用户名已存在");
        }

        return JsonResult.OK(userService.updateEntity(user));
    }

    /**
     * get
     *
     * @param userId 用户id
     * @return {@link JsonResult}
     */
    @GetMapping("{userId}")
    public JsonResult get(@PathVariable("userId") Long userId) {
        return JsonResult.OK(userService.getEntity(userId));
    }

    /**
     * 分页
     *
     * @param user       用户
     * @param pagination 分页
     * @return {@link JsonResult}
     */
    @GetMapping
    public JsonResult page(User user, Pagination pagination) {
        QueryWrapper queryWrapper = QueryBuilder.toQueryWrapper(user);
        return JsonResult.OK(userService.getViewObjectList(queryWrapper, pagination, User.class)).bindPagination(pagination);
    }
}
