package com.cqut.indoor.mapper;

import com.cqut.indoor.model.TDendpoint;
import com.cqut.indoor.model.TDendpointExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component
public interface TDendpointMapper {
    int countByExample(TDendpointExample example);

    int deleteByExample(TDendpointExample example);

    int deleteByPrimaryKey(String ssid);

    int insert(TDendpoint record);

    int insertSelective(TDendpoint record);

    List<TDendpoint> selectByExample(TDendpointExample example);

    TDendpoint selectByPrimaryKey(String ssid);

    int updateByExampleSelective(@Param("record") TDendpoint record, @Param("example") TDendpointExample example);

    int updateByExample(@Param("record") TDendpoint record, @Param("example") TDendpointExample example);

    int updateByPrimaryKeySelective(TDendpoint record);

    int updateByPrimaryKey(TDendpoint record);
}