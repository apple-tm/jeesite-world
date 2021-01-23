package com.wh.js02.mapper;

import com.wh.js02.entity.Js02Menu;
import com.wh.js02.entity.Js02Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.omg.CORBA.INTERNAL;

import java.util.List;

@Mapper
public interface Js02RoleMapper {
    int deleteByPrimaryKey(Integer roleId);

    int insert(Js02Role record);

    int insertSelective(Js02Role record);

    Js02Role selectByPrimaryKey(Integer roleId);

    int updateByPrimaryKeySelective(Js02Role record);

    int updateByPrimaryKey(Js02Role record);

    Js02Role selectRole(Integer roleId);

    List<Js02Role> selectAll();

    Integer roleTotolSize();

    List<Js02Role> selectAllRole(@Param("offSet") Integer offSet,@Param("pageSize") Integer pageSize);

    Integer updateRoleStatus(Integer roleId);

    Integer insertRoleOne(Js02Role js02Role);

/*
    Integer insertRoleAndMenu(@Param("roleId") Integer roleId, @Param("js02MenuList") List<Js02Menu> js02MenuList);
*/


    Integer insertRoleAndMenu(@Param("roleId") int roleId,@Param("menuId") int menuId);

    Integer deleteByRoleId(int roleId);

    List<Js02Role> selectRoleAndMenu(Integer roleId);



}