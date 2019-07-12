package com.cqut.indoor.mapper;

import com.cqut.indoor.model.TDrp;
import com.cqut.indoor.model.TDrpExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component
public interface TDrpMapper {
    int countByExample(TDrpExample example);

    int deleteByExample(TDrpExample example);

    int deleteByPrimaryKey(String rpid);

    int insert(TDrp record);

    int insertSelective(TDrp record);

    List<TDrp> selectByExample(TDrpExample example);

    TDrp selectByPrimaryKey(String rpid);

    int updateByExampleSelective(@Param("record") TDrp record, @Param("example") TDrpExample example);

    int updateByExample(@Param("record") TDrp record, @Param("example") TDrpExample example);

    int updateByPrimaryKeySelective(TDrp record);

    int updateByPrimaryKey(TDrp record);
}