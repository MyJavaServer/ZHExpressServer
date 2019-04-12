package com.zhihui.zhexpress.mapper;

import com.zhihui.zhexpress.model.Order;
import com.zhihui.zhexpress.model.OrderExample;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface OrderMapper {
    long countByExample(OrderExample example);

    int deleteByExample(OrderExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Order record);

    int insertSelective(Order record);

    List<Order> selectByExample(OrderExample example);

    Order selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Order record, @Param("example") OrderExample example);

    int updateByExample(@Param("record") Order record, @Param("example") OrderExample example);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);

    List<Map> getOrderList(@Param("stype") String stype, @Param("num") String num, @Param("repoNum") String repoNum, @Param("userNum") String userNum, @Param("picNum") String picNum, @Param("status") Integer status, @Param("startTime")String startTime, @Param("endTime")String endTime, @Param("orderBy")String orderBy);


}