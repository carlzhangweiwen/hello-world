package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


/**  
 * @author zhangweiwen   
 * @date 2017年9月28日 下午5:52:49 
 */
@RestController
public class DcController {
	@Autowired
	DcClient dcClient;
	
    @GetMapping("/consumer")
    public String dc() {
        return dcClient.consumer();
    }
}
