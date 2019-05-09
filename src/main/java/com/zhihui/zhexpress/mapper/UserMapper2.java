package com.zhihui.zhexpress.mapper;

import com.zhihui.zhexpress.model.User;
import com.zhihui.zhexpress.model.UserExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper2 {
    long countByExample(UserExample example);

    int deleteByExample(UserExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    List<User> selectByExample(UserExample example);

    User selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example);

    int updateByExample(@Param("record") User record, @Param("example") UserExample example);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User checkUserPwd2(@Param("id") int id, @Param("pwd") String pwd);

    int register2(User record);

}