package com.wh.js02.mapper;

import com.wh.js02.entity.Js03User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface Js03UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Js03User record);

    int insertSelective(Js03User record);

    Js03User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Js03User record);

    int updateByPrimaryKey(Js03User record);


    Js03User userLogin(Js03User user);
    List<Js03User> pageQuery(@Param("offset") int offset,@Param("number") int number);
    int queryUserNum();

    int updateStatus(Js03User user);
}