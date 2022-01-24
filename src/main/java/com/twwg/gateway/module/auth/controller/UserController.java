package com.twwg.gateway.module.auth.controller;

import com.diboot.core.vo.JsonResult;
import com.diboot.core.vo.Pagination;
import com.twwg.gateway.base.BaseCustomCrudRestController;
import com.twwg.gateway.module.auth.dto.UserDTO;
import com.twwg.gateway.module.auth.entity.User;
import com.twwg.gateway.module.auth.service.UserService;
import com.twwg.gateway.module.auth.vo.UserDetailVO;
import com.twwg.gateway.module.auth.vo.UserListVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 账户 相关Controller
 *
 * @author MyName
 * @version 1.0
 * @date 2022-01-24
 * Copyright © MyCompany
 */
@RestController
@RequestMapping("/auth/user")
@Slf4j
public class UserController extends BaseCustomCrudRestController<User> {
    @Autowired
    private UserService userService;

    /**
     * 查询ViewObject的分页数据
     * <p>
     * url请求参数示例: /list?field=abc&pageIndex=1&orderBy=abc:DESC
     * </p>
     *
     * @return
     * @throws Exception
     */
    @GetMapping("/list")
    public JsonResult getViewObjectListMapping(UserDTO queryDto, Pagination pagination) throws Exception{
    		return super.getViewObjectList(queryDto, pagination, UserListVO.class);
    }

    /**
     * 根据资源id查询ViewObject
     *
     * @param id ID
     * @return
     * @throws Exception
     */
    @GetMapping("/{id}")
    public JsonResult getViewObjectMapping(@PathVariable("id")Long id) throws Exception{
        return super.getViewObject(id, UserDetailVO.class);
    }

    /**
     * 创建资源对象
     *
     * @param entity
     * @return JsonResult
     * @throws Exception
     */
    @PostMapping("/")
    public JsonResult createEntityMapping(@Valid @RequestBody User entity) throws Exception {
        return super.createEntity(entity);
    }

    /**
     * 根据ID更新资源对象
     *
     * @param entity
     * @return JsonResult
     * @throws Exception
     */
    @PutMapping("/{id}")
    public JsonResult updateEntityMapping(@PathVariable("id")Long id, @Valid @RequestBody User entity) throws Exception {
        return super.updateEntity(id, entity);
    }

    /**
     * 根据id删除资源对象
     *
     * @param id
     * @return
     * @throws Exception
     */
    @DeleteMapping("/{id}")
    public JsonResult deleteEntityMapping(@PathVariable("id")Long id) throws Exception {
        return super.deleteEntity(id);
    }

} 