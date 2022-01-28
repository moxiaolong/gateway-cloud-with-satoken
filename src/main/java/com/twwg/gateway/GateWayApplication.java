package com.twwg.gateway;

import com.twwg.common.core.EnableMicroServiceCommonConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 网关应用程序
 *
 * @author dragon
 * @date 2021/10/18
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableMicroServiceCommonConfig
@EnableFeignClients("com.twwg.api")
public class GateWayApplication {
    public static void main(String[] args) {
        SpringApplication.run(GateWayApplication.class, args);
    }

}
