package com.twwg.gateway.module.auth.dto;

import lombok.Data;

/**
 * 有权限uri dto
 *
 * @author dragon
 * @date 2022/01/24
 */
@Data
public class HasUriPermissionDto {
    /**
     * uri
     */
    private String uri;
    /**
     * 方法
     */
    private String method;

    private boolean hasPermission;
}
