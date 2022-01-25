package com.twwg.gateway.module.auth.controller;

import cn.dev33.satoken.router.SaRouter;
import cn.dev33.satoken.stp.StpUtil;
import com.diboot.core.vo.JsonResult;
import com.twwg.gateway.module.auth.dto.HasUriPermissionDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/authCheck")
public class AuthCheckController {

    /**
     * 获取角色列表
     *
     * @return {@link List}<{@link String}>
     */
    @GetMapping("getRoleList")
    public JsonResult getRoleList() {
        return JsonResult.OK(StpUtil.getRoleList());

    }

    /**
     * 获得权限列表
     *
     * @return {@link List}<{@link String}>
     */
    @GetMapping("getPermissionList")
    public JsonResult getPermissionList() {
        return JsonResult.OK(StpUtil.getPermissionList());
    }

    /**
     * 是否有网址访问权
     *
     * @param hasUriPermissionDtoList uri dto许可列表
     * @return {@link Map}<{@link HasUriPermissionDto}, {@link Boolean}>
     */
    @GetMapping("hasUrlPermission")
    public JsonResult hasUrlPermission(@RequestBody List<HasUriPermissionDto> hasUriPermissionDtoList) {
        List<String> permissionList = StpUtil.getPermissionList();
        for (HasUriPermissionDto dto : hasUriPermissionDtoList) {
            dto.setHasPermission(false);
            String method = dto.getMethod();
            String uri = dto.getUri();
            for (String permission : permissionList) {
                if (permission.startsWith(method)) {
                    boolean match = SaRouter.isMatch(permission.substring(method.length() + 1), uri);
                    if (match) {
                        dto.setHasPermission(true);
                        break;
                    }
                }else if (permission.startsWith("*")){
                    boolean match = SaRouter.isMatch(permission.substring(2), uri);
                    if (match) {
                        dto.setHasPermission(true);
                        break;
                    }
                }
            }
        }
        return JsonResult.OK(hasUriPermissionDtoList);
    }
}
