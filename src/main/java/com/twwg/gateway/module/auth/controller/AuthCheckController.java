package com.twwg.gateway.module.auth.controller;

import cn.dev33.satoken.router.SaRouter;
import cn.dev33.satoken.stp.StpUtil;
import com.twwg.gateway.module.auth.dto.HasUriPermissionDto;
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
    @RequestMapping("getRoleList")
    public List<String> getRoleList() {
        return StpUtil.getRoleList();
    }

    /**
     * 获得权限列表
     *
     * @return {@link List}<{@link String}>
     */
    @RequestMapping("getPermissionList")
    public List<String> getPermissionList() {
        return StpUtil.getPermissionList();
    }

    /**
     * 是否有网址访问权
     *
     * @param hasUriPermissionDtoList uri dto许可列表
     * @return {@link Map}<{@link HasUriPermissionDto}, {@link Boolean}>
     */
    @RequestMapping("hasUrlPermission")
    public List<HasUriPermissionDto> hasUrlPermission(List<HasUriPermissionDto> hasUriPermissionDtoList) {
        List<String> permissionList = StpUtil.getPermissionList();
        for (HasUriPermissionDto dto : hasUriPermissionDtoList) {
            dto.setHasPermission(false);
            String method = dto.getMethod();
            String uri = dto.getUri();
            for (String permission : permissionList) {
                if (permission.startsWith(method)) {
                    boolean match = SaRouter.isMatch(permission.substring(method.length()+1), uri);
                    if (match){
                        dto.setHasPermission(true);
                        break;
                    }
                }
            }
        }
        return hasUriPermissionDtoList;
    }
}
