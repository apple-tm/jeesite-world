package com.wh.js02.mapper;

import com.wh.js02.entity.Js03Log;
import com.wh.js02.entity.Js03User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface Js03LogMapper {
    int deleteByPrimaryKey(Integer logId);

    int insert(Js03Log record);

    int insertSelective(Js03Log record);

    Js03Log selectByPrimaryKey(Integer logId);

    int updateByPrimaryKeySelective(Js03Log record);

    int updateByPrimaryKey(Js03Log record);

    int  insertLog(Js03Log record);

    //查询日志
    List<Js03Log> query(@Param("offset") int offset, @Param("number") int number);

    //删除日志
    int deleteById(int id);

    //模糊查询日志
    List<Js03Log> fuzzyQuery(@Param("offset") int offset, @Param("number") int number, @Param("searchKey") String searchKey);

    //总记录数
    int total();

    //模糊总记录数
    int totalFuzzy(String searchKey);


}