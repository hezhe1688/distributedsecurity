package com.hezhe.security.distributed.uaa;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableHystrix
@EnableFeignClients(basePackages = {"com.hezhe.security.distributed.uaa"})
@MapperScan("com.hezhe.security.distributed.uaa.mapper")
public class UAAServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(UAAServerApplication.class, args);
    }
}
