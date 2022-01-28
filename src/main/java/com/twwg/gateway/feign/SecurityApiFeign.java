package com.twwg.gateway.feign;


import com.twwg.gateway.feign.dto.UserDetail;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import reactor.core.publisher.Mono;

import java.util.Set;

/**
 * 安全api
 *
 * @author dragon
 * @date 2022/01/27
 */
@RequestMapping("/security/")
@FeignClient(name = "security")
public interface SecurityApiFeign {

    /**
     * 获取 身份验证信息
     *
     * @return {@link UserDetail}
     */
    @RequestMapping(value = "doGetAuthenticationInfo",method = RequestMethod.POST)
    Mono<UserDetail> doGetAuthenticationInfo();

    /**
     * get 权限
     *
     * @return {@link Set}<{@link String}>
     */
    @RequestMapping(value = "doGetAuthorizationInfo",method = RequestMethod.POST)
    Mono<Set<String>> doGetAuthorizationInfo();

}
