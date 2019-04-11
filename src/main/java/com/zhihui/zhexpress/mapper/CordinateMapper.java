package com.zhihui.zhexpress.mapper;

import com.zhihui.zhexpress.model.Cordinate;
import com.zhihui.zhexpress.model.CordinateExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CordinateMapper {
    long countByExample(CordinateExample example);

    int deleteByExample(CordinateExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Cordinate record);

    int insertSelective(Cordinate record);

    List<Cordinate> selectByExample(CordinateExample example);

    Cordinate selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Cordinate record, @Param("example") CordinateExample example);

    int updateByExample(@Param("record") Cordinate record, @Param("example") CordinateExample example);

    int updateByPrimaryKeySelective(Cordinate record);

    int updateByPrimaryKey(Cordinate record);
}