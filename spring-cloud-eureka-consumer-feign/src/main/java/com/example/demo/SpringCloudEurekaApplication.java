package com.example.demo;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * 创建一个服务消费者工程
 * 参考http://blog.didispace.com/spring-cloud-starter-dalston-2-3/
 * 
 * @author zhangweiwen   
 * @date 2017年9月28日 下午5:48:24
 */
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class SpringCloudEurekaApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(SpringCloudEurekaApplication.class)
        .web(true).run(args);
	}
}
