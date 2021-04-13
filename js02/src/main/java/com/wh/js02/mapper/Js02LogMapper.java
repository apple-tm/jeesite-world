package com.wh.js02.mapper;

import com.wh.js02.entity.Js02Log;

public interface Js02LogMapper {
    int deleteByPrimaryKey(Integer js02LogId);

    int insert(Js02Log record);

    int insertSelective(Js02Log record);

    Js02Log selectByPrimaryKey(Integer js02LogId);

    int updateByPrimaryKeySelective(Js02Log record);

    int updateByPrimaryKey(Js02Log record);
}