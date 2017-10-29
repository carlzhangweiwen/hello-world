package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


/**  
 * @author zhangweiwen   
 * @date 2017年9月28日 下午5:52:49 
 */
@RestController
public class DcController {
    @Autowired
    RestTemplate restTemplate;
    @GetMapping("/consumer")
    public String dc() {
        String url = "http://eureka-client/dc";
        System.out.println(url);
        return restTemplate.getForObject(url, String.class);
    }
}
