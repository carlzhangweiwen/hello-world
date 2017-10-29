package com.autumnsinger.core.service;

import com.autumnsinger.AutumnSingerApplicationTests;
import com.autumnsinger.dal.model.JijinDaily;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by carl on 17-7-25.
 */

public class JijinSpiderServiceTest extends AutumnSingerApplicationTests {
    @Autowired
    JijinSpiderService jijinSpiderService;

    @Autowired
    JijinQueryService jijinQueryService;

    @Transactional
    @Rollback
//    @Commit
    @Test
    public void spiderDaily() throws Exception {
        jijinSpiderService.spider("2017-08-11");
    }

    @Transactional
    @Rollback
    @Test
    public void spiderMonth() throws Exception {
        jijinSpiderService.spiderMonth("2017","05");
    }

    @Test
    public void query(){
        List<JijinDaily> jijinDailies = jijinQueryService.queryDaily("2017-10-07");
        System.out.println(jijinDailies);
        Assert.assertNotNull(jijinDailies);

    }

}