package com.cqut.indoor.service;

import com.cqut.indoor.mapper.TDendpointMapper;
import com.cqut.indoor.mapper.TDmeasureMapper;
import com.cqut.indoor.mapper.TDrpMapper;
import com.cqut.indoor.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class LocationService {
    @Autowired
    TDendpointMapper endPointMapper;

    @Autowired
    TDmeasureMapper measureMapper;

    @Autowired
    TDrpMapper rpMapper;

    @Cacheable(cacheNames = "rplist")
    public List<TDrp> getRpList(){
        TDrpExample example=new TDrpExample();
        example.createCriteria().andRpidIsNotNull();
        return rpMapper.selectByExample(example);
    }
    @Cacheable(cacheNames = "rp")
    public TDrp getrp(String rpid){
        return rpMapper.selectByPrimaryKey(rpid);
    }



    @Cacheable(cacheNames = "measure")
    public TDmeasure getMeasure(String rpid, String apid){
        TDmeasureExample example=new TDmeasureExample();
        example.createCriteria().andRpidEqualTo(rpid).andApidEqualTo(apid);
        List<TDmeasure> measureList=measureMapper.selectByExample(example);
        if (!measureList.isEmpty())
            return measureList.get(0);
        else
            return new TDmeasure();
    }
}
