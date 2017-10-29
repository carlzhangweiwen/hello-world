package com.autumnsinger.web.controller;

import com.autumnsinger.core.service.JijinSpiderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by carl on 17-7-15.
 */
@Controller
public class HomeController {
    @Autowired
    JijinSpiderService jijinSpiderService;

    @GetMapping("/")
    public String home(){
        return "index";
    }

    @GetMapping("/jijin")
    public String jijin(){
        return "index";
    }
}
