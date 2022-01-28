package com.twwg.gateway.fliter;

import com.twwg.api.SecurityApiFeign;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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

        //假装如果没有权限 就响应无权限 http://localhost:8888/api/your-service-name/user/ban
        for (String aAllowUri : allowUri) {
            if (path.startsWith(aAllowUri)) {
                return chain.filter(exchange);
            }
        }
        List<String> tokens = request.getHeaders().get("token");
        if (tokens!=null&&tokens.size()>0){
            String token = tokens.get(0);
            if (token!=null&&!token.isEmpty()){
                Set<String> permission = securityApiFeign.doGetAuthorizationInfo();


            }
        }

        DataBuffer bodyDataBuffer = response.bufferFactory().wrap("无权限".getBytes());
        response.getHeaders().add("Content-Type", "text/plain;charset=UTF-8");
        return response.writeWith(Mono.just(bodyDataBuffer));

    }

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE;
    }
}
