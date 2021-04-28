package com.wh.js02.mapper;

import com.wh.js02.entity.Js03Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface Js03RoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Js03Role record);

    int insertSelective(Js03Role record);

    Js03Role selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Js03Role record);

    int updateByPrimaryKey(Js03Role record);


    int insertRole(Js03Role record);

    int updateById(Js03Role record);

    int deleteById(int roleId);

    List<Js03Role> select(@Param("offset") int offset,@Param("pageSize") int pageSize);

    List<Js03Role> fuzzyQuery(@Param("offset") Integer offset, @Param("pageSize") Integer pageSize,@Param("searchKey") String searchKey);

}