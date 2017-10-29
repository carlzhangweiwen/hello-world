package com.autumnsinger.core.service;

import com.autumnsinger.dal.mapper.JijinDailyMapper;
import com.autumnsinger.dal.model.JijinDaily;
import com.autumnsinger.dal.model.JijinDailyExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by carl on 17-8-8.
 */
@Service
public class JijinQueryService {
    @Autowired
    private JijinDailyMapper jijinDailyMapper;

    /**
     * 查询每日基金
     * @param dateStr eg. "2017-07-21"
     * @return
     */
    public List<JijinDaily> queryDaily(String dateStr){
        JijinDailyExample example = new JijinDailyExample();
        example.createCriteria().andSearchdateEqualTo(dateStr);
        List<JijinDaily> jijinDailies = jijinDailyMapper.selectByExample(example);

        return jijinDailies;
    }
}
