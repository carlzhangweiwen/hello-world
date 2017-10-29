package com.autumnsinger.core.service;

import com.autumnsinger.AutumnSingerApplicationTests;
import com.autumnsinger.dal.mapper.FundProductInfoMapper;
import com.autumnsinger.dal.model.FundProductInfo;
import com.autumnsinger.dal.model.FundProductInfoExample;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

/**
 * Created by carl on 17-9-13.
 */
public class TianTianSpiderServiceTest extends AutumnSingerApplicationTests {
    @Autowired
    TianTianSpiderService tianTianSpiderService;
    @Test
    @Transactional
    @Rollback
    public void test1() throws IOException {
        tianTianSpiderService.spider("2016-09-13", "2016-09-13");
    }

    @Autowired
    FundProductInfoMapper  fundProductInfoMapper;
    @Test
    public void testQuery(){
        FundProductInfoExample example = new FundProductInfoExample();
        example.setLimit(10);
        example.setOffset(5);
        example.setOrderByClause(" id");
//        example.createCriteria().;
        List<FundProductInfo> fundProductInfos = fundProductInfoMapper.selectByExample(example);
        for (FundProductInfo pro: fundProductInfos) {
            System.out.println(pro.getId() + pro.getProductName());
        }
    }

}
