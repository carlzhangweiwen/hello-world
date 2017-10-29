package com.example.demo;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * 创建“服务注册中心”
 * 参考http://blog.didispace.com/spring-cloud-starter-dalston-1/
 * 
 * @author zhangweiwen   
 * @date 2017年9月28日 下午5:48:24
 */
@EnableEurekaServer
@SpringBootApplication
public class SpringCloudEurekaApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(SpringCloudEurekaApplication.class)
        .web(true).run(args);
	}
}
