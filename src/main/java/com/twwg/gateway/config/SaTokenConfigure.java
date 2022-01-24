package com.twwg.gateway.config;

import cn.dev33.satoken.context.SaHolder;
import cn.dev33.satoken.jwt.StpLogicJwtForStateless;
import cn.dev33.satoken.reactor.filter.SaReactorFilter;
import cn.dev33.satoken.router.SaRouter;
import cn.dev33.satoken.stp.StpLogic;
import cn.dev33.satoken.stp.StpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

/**
 * [Sa-Token 权限认证] 全局配置类
 *
 * @author dragon
 * @date 2022/01/24
 */
@Configuration
@Slf4j
public class SaTokenConfigure {
    @Bean
    public StpLogic getStpLogicJwt() {
        return new StpLogicJwtForStateless();
    }

    /**
     * 注册 [Sa-Token全局过滤器]
     */
    @Bean
    public SaReactorFilter getSaReactorFilter() {
        return new SaReactorFilter() {
            @Override
            public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
                return super.filter(exchange, chain);
            }
        }
                // 指定 [拦截路由]
                .addInclude("/**")
                // 指定 [放行路由]
                .addExclude("/favicon.ico")
                .addExclude("/auth/login/**")
                // 指定[认证函数]: 每次请求执行
                .setAuth(obj -> {
                    if (!hasPermission()) {
                        throw new RuntimeException("无权限");
                    }
                })
                // 指定[异常处理函数]：每次[认证函数]发生异常时执行此函数
                .setError(e -> {
                    log.info(e.getMessage());
                    return e.getMessage();
                });
    }

    /**
     * 有权限
     *
     * @return boolean
     */
    private boolean hasPermission() {
        StpUtil.checkLogin();
        boolean admin = StpUtil.hasRole("admin");
        if (admin) {
            return true;
        }
        List<String> permissionList = StpUtil.getPermissionList();
        String method = SaHolder.getRequest().getMethod();
        permissionList = permissionList.stream()
                //过滤method
                .filter(t -> t.startsWith(method))
                //截取url部分  POST:/auth/isLogin -> /auth/isLogin
                .map(t -> t.substring(method.length()+1))
                .collect(Collectors.toList());
        return SaRouter.isMatchCurrURI(permissionList);
    }


}