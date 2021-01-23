package com.wh.js02.mapper;

import com.wh.js02.entity.Js02User;
import com.wh.js02.req.PageDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface Js02UserMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(Js02User record);

    int insertSelective(Js02User record);

    Js02User selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(Js02User record);

    int updateByPrimaryKey(Js02User record);

    /*查询所有用户 分页*/
    List<Js02User> selectAllUser(@Param("offset") Integer offset, @Param("pageSize") Integer pageSize);

    Integer totolSize();

    /*新增用户*/
    Integer insertUserOne(Js02User js02User);

    /*模糊查询*/
    List<Js02User> fuzzyQuery(@Param("offset") Integer offset, @Param("pageSize") Integer pageSize,@Param("searchKey") String searchKey);

    /*修改用户状态*/
    Integer updateUserStatus(Integer userId);

    /*模拟查询总的用户数量*/
    Integer totolSizeFuzzy(String SearchKey);


    Js02User login(Js02User js02User);

    Js02User selectUserAll(String userPhone);

}