package com.wh.js02.mapper;

import com.wh.js02.entity.Js03Role;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface Js03RoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Js03Role record);

    int insertSelective(Js03Role record);

    Js03Role selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Js03Role record);

    int updateByPrimaryKey(Js03Role record);
}