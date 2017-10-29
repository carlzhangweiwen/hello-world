package com.autumnsinger.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.autumnsinger.dal.model.JijinDaily;
import com.autumnsinger.core.service.JijinQueryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by carl on 17-8-8.
 */
@RestController
public class JijinRestController {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private JijinQueryService jijinQueryService;

    @GetMapping("/queryDaily")
    public String queryDaily(String dateStr){
        logger.info("dateStr:{}", dateStr);
        List<JijinDaily> jijinDailies = jijinQueryService.queryDaily(dateStr);

        return JSONObject.toJSONString(jijinDailies);
    }
}
