package com.twwg.gateway.fliter;

import cn.hutool.jwt.JWT;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import java.util.Map;
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
    private WebClient.Builder webClientBuilder;
    @Autowired
    private ObjectMapper objectMapper;
    private final JWT jwt = JWT.create();

    List<String> allowUri = new ArrayList<>();

    AuthFilter() {
        allowUri.add("/login/captcha");
        allowUri.add("/login");
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
        //token校验
        List<String> tokens = request.getHeaders().get("token");
        if (tokens != null && tokens.size() > 0) {
            String token = tokens.get(0);
            if (token != null && !token.isEmpty()) {
                Mono<Void> mono = webClientBuilder.build().get().uri("lb://iot-cloud-auth/auth/pathList").header("token", token).retrieve().bodyToMono(Map.class).flatMap(map -> {
                    ArrayList<Map<String, String>> dataString = (ArrayList) map.get("data");
                    if (checkPermission(dataString, path, request.getMethodValue())) {
                        //过了这里 说明token是有效的 解析id 放入请求头给服务使用
                        String id = jwt.parse(token).getPayload("id").toString();
                        ServerHttpRequest newReq = request.mutate().header("userId", id).build();
                        return chain.filter(exchange.mutate().request(newReq).build());
                    }
                    response.setStatusCode(HttpStatus.UNAUTHORIZED);
                    return exchange.getResponse().setComplete();
                });
                return mono.doOnError((throwable) -> {
                    response.setStatusCode(HttpStatus.UNAUTHORIZED);
                    String message = throwable.getMessage();
                    log.error(message);

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

    private boolean checkPermission(List<Map<String, String>> authPathList, String path, String method) {
        List<Map<String, String>> collect = authPathList.stream().filter(authPath -> authPath.get("method").equals(method) || "*".equals(authPath.get("method"))).collect(Collectors.toList());
        if (collect.size() == 0) {
            return false;
        }
        for (Map<String, String> map : collect) {
            String uri = map.get("uri");
            uri = uri.replace("*", ".*");
            if (Pattern.matches(uri, path)) {
                return true;
            }
        }

        return false;
    }
}
