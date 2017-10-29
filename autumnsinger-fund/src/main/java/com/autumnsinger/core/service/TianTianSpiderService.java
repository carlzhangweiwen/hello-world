package com.autumnsinger.core.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.autumnsinger.common.HttpClientUtil;
import com.autumnsinger.common.domain.Response;
import com.autumnsinger.manager.FundProductInfoManager;
import com.autumnsinger.dal.model.FundProductInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;

/**
 * Created by carl on 17-8-12.
 * 爬取天天基金數據
 */
@Service
public class TianTianSpiderService {
    private Logger logger = LoggerFactory.getLogger(getClass());

    private String url = "http://fund.eastmoney.com/data/rankhandler.aspx?" +
            "op=ph&dt=kf&ft=all&rs=&gs=0&sc=zzf&st=desc" +
            "&sd=%s&ed=%s&qdii=&tabSubtype=,,,,," +
            "&pi=%s&pn=50&dx=1&v=0.594148541662296";


    @Autowired
    private FundProductInfoManager fundProductInfoManager;


    /**
     *
     * @param startime 2016-09-13
     * @param endtime 2016-09-13
     * @throws IOException
     */
    public void spider(String startime, String endtime){
        spider(startime,endtime,1);
    }

    /**
     *
     * @param startime
     * @param endtime
     * @param pageIndex 要爬取的頁數
     * @throws IOException
     */
    public void spider(String startime, String endtime, int pageIndex) {
        logger.info("開始爬取時間:{},結束爬取時間:{},當前將爬取頁數：{}",new Object[]{startime,endtime,pageIndex});
        HashMap<String, String> header = new HashMap<>();
        header.put("Referer","http://fund.eastmoney.com/data/fundranking.html");
        String furl = String.format(url, startime,endtime,pageIndex);
        Response response = null;
        try {
            response = HttpClientUtil.post(furl,null);
        } catch (IOException e) {
            logger.error(e.getMessage(),e);
            return;
        }
        String content = response.getContent();

        content = content.replace("var rankData = ","")
                .replace(";","");
        JSONObject json = JSONObject.parseObject(content);

        JSONArray datas = json.getJSONArray("datas");
        for(int i=0; i< datas.size(); i++){
            String str = datas.getString(i);
            String[] split = str.split(",");
            FundProductInfo productInfo = new FundProductInfo();
            productInfo.setProductCode(split[0]);
            productInfo.setProductName(split[1]);
            productInfo.setNetWorthDate(split[3]);
            productInfo.setNetWorth(percentStr2BigDecimal(split[4]));
            productInfo.setGrowRateDaily(percentStr2BigDecimal(split[6]));
            productInfo.setGrowRateWeekly(percentStr2BigDecimal(split[7]));
            productInfo.setGrowRate1month(percentStr2BigDecimal(split[8]));
            productInfo.setGrowRate3month(percentStr2BigDecimal(split[9]));
            productInfo.setGrowRate6month(percentStr2BigDecimal(split[10]));
            productInfo.setGrowRate1year(percentStr2BigDecimal(split[11]));
            productInfo.setGrowRate2year(percentStr2BigDecimal(split[12]));
            productInfo.setGrowRate3year(percentStr2BigDecimal(split[13]));
            productInfo.setGrowRateThisYear(percentStr2BigDecimal(split[14]));
            productInfo.setGrowRateFromBorn(percentStr2BigDecimal(split[15]));

            fundProductInfoManager.saveOrUpdate(productInfo);
        }
        Integer currPageIndex = json.getInteger("pageIndex");//當前頁
        Integer allPages = json.getInteger("allPages");//總頁數
        logger.info("currPageIndex:{},allPages:{}", new Object[]{currPageIndex, allPages});
        if(currPageIndex < allPages){
            spider(startime,endtime, currPageIndex + 1);
        }

    }

    private BigDecimal percentStr2BigDecimal(String str){
        if(StringUtils.isEmpty(str)){
            return null;
        }else{
            return new BigDecimal(str);
        }
    }
}
