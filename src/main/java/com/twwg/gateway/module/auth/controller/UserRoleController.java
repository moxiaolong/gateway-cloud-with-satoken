package com.twwg.gateway.module.auth.controller;

import com.diboot.core.vo.*;
import com.diboot.iam.annotation.BindPermission;
import com.diboot.iam.annotation.Log;
import com.diboot.iam.annotation.OperationCons;
import com.example.demo.controller.BaseCustomCrudRestController;
import com.example.demo.dto.auth.UserRoleDTO;
import com.example.demo.entity.auth.UserRole;
import com.example.demo.service.auth.UserRoleService;
import com.example.demo.vo.auth.UserRoleDetailVO;
import com.example.demo.vo.auth.UserRoleListVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 用户角色中间表 相关Controller
 *
 * @author MyName
 * @version 1.0
 * @date 2022-01-24
 * Copyright © MyCompany
 */
@RestController
@RequestMapping("/auth/userRole")
@BindPermission(name = "用户角色中间表", code="AuthUserRole")
@Slf4j
public class UserRoleController extends BaseCustomCrudRestController<UserRole> {
    @Autowired
    private UserRoleService userRoleService;

    /**
     * 查询ViewObject的分页数据
     * <p>
     * url请求参数示例: /list?field=abc&pageIndex=1&orderBy=abc:DESC
     * </p>
     *
     * @return
     * @throws Exception
     */
    @Log(operation = OperationCons.LABEL_LIST)
    @BindPermission(name = OperationCons.LABEL_LIST, code = OperationCons.CODE_LIST)
    @GetMapping("/list")
    public JsonResult getViewObjectListMapping(UserRoleDTO queryDto, Pagination pagination) throws Exception{
    		return super.getViewObjectList(queryDto, pagination, UserRoleListVO.class);
    }

    /**
     * 根据资源id查询ViewObject
     *
     * @param id ID
     * @return
     * @throws Exception
     */
    @Log(operation = OperationCons.LABEL_DETAIL)
    @BindPermission(name = OperationCons.LABEL_DETAIL, code = OperationCons.CODE_DETAIL)
    @GetMapping("/{id}")
    public JsonResult getViewObjectMapping(@PathVariable("id")Long id) throws Exception{
        return super.getViewObject(id, UserRoleDetailVO.class);
    }

    /**
     * 创建资源对象
     *
     * @param entity
     * @return JsonResult
     * @throws Exception
     */
    @Log(operation = OperationCons.LABEL_CREATE)
    @BindPermission(name = OperationCons.LABEL_CREATE, code = OperationCons.CODE_CREATE)
    @PostMapping("/")
    public JsonResult createEntityMapping(@Valid @RequestBody UserRole entity) throws Exception {
        return super.createEntity(entity);
    }

    /**
     * 根据ID更新资源对象
     *
     * @param entity
     * @return JsonResult
     * @throws Exception
     */
    @Log(operation = OperationCons.LABEL_UPDATE)
    @BindPermission(name = OperationCons.LABEL_UPDATE, code = OperationCons.CODE_UPDATE)
    @PutMapping("/{id}")
    public JsonResult updateEntityMapping(@PathVariable("id")Long id, @Valid @RequestBody UserRole entity) throws Exception {
        return super.updateEntity(id, entity);
    }

    /**
     * 根据id删除资源对象
     *
     * @param id
     * @return
     * @throws Exception
     */
    @Log(operation = OperationCons.LABEL_DELETE)
    @BindPermission(name = OperationCons.LABEL_DELETE, code = OperationCons.CODE_DELETE)
    @DeleteMapping("/{id}")
    public JsonResult deleteEntityMapping(@PathVariable("id")Long id) throws Exception {
        return super.deleteEntity(id);
    }

} 