package com.cqut.indoor.mapper;

import com.cqut.indoor.model.TDmeasure;
import com.cqut.indoor.model.TDmeasureExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component
public interface TDmeasureMapper {
    int countByExample(TDmeasureExample example);

    int deleteByExample(TDmeasureExample example);

    int insert(TDmeasure record);

    int insertSelective(TDmeasure record);

    List<TDmeasure> selectByExample(TDmeasureExample example);

    int updateByExampleSelective(@Param("record") TDmeasure record, @Param("example") TDmeasureExample example);

    int updateByExample(@Param("record") TDmeasure record, @Param("example") TDmeasureExample example);
}