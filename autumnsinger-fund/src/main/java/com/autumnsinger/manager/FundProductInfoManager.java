package com.autumnsinger.manager;

import com.autumnsinger.dal.mapper.FundProductInfoMapper;
import com.autumnsinger.dal.model.FundProductInfo;
import com.autumnsinger.dal.model.FundProductInfoExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by carl on 17-9-29.
 */
@Repository
public class FundProductInfoManager {
    @Autowired
    private FundProductInfoMapper fundProductInfoMapper;
    public int saveOrUpdate(FundProductInfo fundProductInfo){
        FundProductInfoExample example = new FundProductInfoExample();
        example.createCriteria().andProductCodeEqualTo(fundProductInfo.getProductCode());
        List<FundProductInfo> fundProductInfos = fundProductInfoMapper
                .selectByExample(example);

        if(fundProductInfos != null && fundProductInfos.size()>0){
            fundProductInfo.setId(fundProductInfos.get(0).getId());
            return fundProductInfoMapper.updateByPrimaryKeySelective(fundProductInfo);
        }else {
            return fundProductInfoMapper.insert(fundProductInfo);
        }
    }
}
