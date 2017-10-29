package com.autumnsinger.core.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.autumnsinger.common.HttpClientUtil;
import com.autumnsinger.common.domain.Response;
import com.autumnsinger.dal.mapper.JijinDailyMapper;
import com.autumnsinger.dal.mapper.JijinMonthlyMapper;
import com.autumnsinger.dal.model.JijinDaily;
import com.autumnsinger.dal.model.JijinMonthly;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * Created by carl on 17-7-16.
 */
@Service
public class JijinSpiderService {
    private Logger logger = LoggerFactory.getLogger(getClass());

    private static String url = "http://www.sse.com.cn/market/funddata/overview/day/";

    private static String dayUrl = "http://query.sse.com.cn/marketdata/tradedata/queryTradingByProdTypeData.do?jsonCallBack=jsonpCallback76083&searchDate=%s&prodType=jj&_=1500172095636";

    private static String monthRefUrl = "http://www.sse.com.cn/market/funddata/overview/monthly/";
    private static String monthUrl = "http://query.sse.com.cn/marketdata/tradedata/queryNewMonthlyTrade.do?jsonCallBack=jsonpCallback99092&prodType=jj&inYear=%s&_=1500776977785";


    @Autowired
    JijinDailyMapper jijinDailyMapper;

    @Autowired
    JijinMonthlyMapper jijinMonthlyMapper;

    /**
     *
     * 爬取某天的數據
     * @param theDay eg.2017-07-14
     */
    public void spider(String theDay){
        try {
            logger.info("開始爬取每日基金數據:{}", theDay);
            HashMap<String, String> header = new HashMap<>();
            //設置消息頭，不然無法通過對方的校驗
            header.put("Referer",url);

            Response response = HttpClientUtil.get(String.format(dayUrl, theDay), null, header);
            String s = response.getContent();
            String content = s.replace("jsonpCallback76083(","")
                    .replace(")","");
            JSONObject json = JSONObject.parseObject(content);

            JSONArray result = json.getJSONArray("result");

            for(int i = 0; i < result.size(); i++){
                //將結果集映射爲java對象
                JijinDaily jijinDaily = JSONObject.toJavaObject(result.getJSONObject(i), JijinDaily.class);

                //只插入不爲空的數據;注意insert 和 insertSelective的區別
                jijinDailyMapper.insertSelective(jijinDaily);
            }

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

    }


    /**
     *爬取某年某月的數據
     * @param inYear 2017
     * @param theMonth 06
     */
    public void spiderMonth(String inYear, String theMonth){
        try {
            String mStr = String.format("%s-%s",inYear, theMonth);//2017-06
            logger.info("開始爬取每日基金數據:{}", mStr);
            HashMap<String, String> header = new HashMap<>();
            //設置消息頭，不然無法通過對方的校驗
            header.put("Referer",monthRefUrl);

            Response response = HttpClientUtil.get(String.format(monthUrl, mStr), null, header);
            String s = response.getContent();
            String content = s.replace("jsonpCallback99092(","")
                    .replace(")","");
            JSONObject json = JSONObject.parseObject(content);

            JSONArray result = json.getJSONArray("result");

            for(int i = 0; i < result.size(); i++){
                //將結果集映射爲java對象
                JijinMonthly jijinMonthly = JSONObject.toJavaObject(result.getJSONObject(i), JijinMonthly.class);

                jijinMonthly.setInyear(inYear);
                //只插入不爲空的數據;注意insert 和 insertSelective的區別
                jijinMonthlyMapper.insertSelective(jijinMonthly);
            }

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }


}
