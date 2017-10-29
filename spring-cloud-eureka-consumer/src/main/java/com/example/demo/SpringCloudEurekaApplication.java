package com.example.demo;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * 创建一个服务消费者工程
 * 参考http://blog.didispace.com/spring-cloud-starter-dalston-2-1/
 * 
 * @author zhangweiwen   
 * @date 2017年9月28日 下午5:48:24
 */
@EnableDiscoveryClient
@SpringBootApplication
public class SpringCloudEurekaApplication {
	
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	public static void main(String[] args) {
		new SpringApplicationBuilder(SpringCloudEurekaApplication.class)
        .web(true).run(args);
	}
}
