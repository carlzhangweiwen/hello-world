package com.example.demo;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 创建“服务提供方”
 * 创建提供服务的客户端，并向服务注册中心注册自己。
 * 参考http://blog.didispace.com/spring-cloud-starter-dalston-1/
 * 
 * @author zhangweiwen   
 * @date 2017年9月28日 下午5:48:24
 */
@EnableDiscoveryClient
@SpringBootApplication
public class SpringCloudEurekaApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(SpringCloudEurekaApplication.class)
        .web(true).run(args);
	}
}
