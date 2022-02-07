package com.twwg.gateway.fliter;

import com.twwg.gateway.feign.SecurityApiFeign;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * 日志过滤器
 *
 * @author dragon
 * @date 2021/10/20
 */
@Component
@Slf4j
public class AuthFilter implements GlobalFilter, Ordered {

    @Autowired
    SecurityApiFeign securityApiFeign;
    @Autowired
    private WebClient.Builder webClientBuilder;

    List<String> allowUri = new ArrayList<>();

    AuthFilter() {
        allowUri.add("/security/captcha");
        allowUri.add("/security/noCaptchaLogin");
        allowUri.add("/security/login");
        allowUri.add("/security/appLogin");
        allowUri.add("/security/logout");
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();
        String path = exchange.getRequest().getURI().getPath();

        //放行
        for (String aAllowUri : allowUri) {
            if (path.equals(aAllowUri)) {
                return chain.filter(exchange);
            }
        }
        List<String> tokens = request.getHeaders().get("token");
        if (tokens != null && tokens.size() > 0) {
            String token = tokens.get(0);
            if (token != null && !token.isEmpty()) {
                return webClientBuilder.build().post().uri("lb://security/security/security/doGetAuthorizationInfo").header("token", token).retrieve().bodyToMono(Set.class).flatMap(authSet -> {
                    if (checkPermission(authSet, path, request.getMethodValue())) {
                        return chain.filter(exchange);
                    }
                    response.setStatusCode(HttpStatus.UNAUTHORIZED);
                    return exchange.getResponse().setComplete();

                });
            }
        }

        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        return exchange.getResponse().setComplete();
    }

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE;
    }

    private boolean checkPermission(Set<String> permission, String path, String method) {
        List<String> permissionList = permission.stream()
                //过滤method
                .filter(t -> t.startsWith(method) || t.startsWith("*"))
                //截取url部分  POST:/auth/isLogin -> /auth/isLogin
                .map(t -> {
                    if (t.startsWith("*")) {
                        return t.substring(2);
                    }
                    return t.substring(method.length() + 1);
                }).collect(Collectors.toList());

        for (String aPermission : permissionList) {
            if (Pattern.matches(aPermission, path)) {
                return true;
            }
        }
        return false;
    }
}
