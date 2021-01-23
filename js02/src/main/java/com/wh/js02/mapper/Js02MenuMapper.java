package com.wh.js02.mapper;

import com.wh.js02.entity.Js02Menu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface Js02MenuMapper {
    int deleteByPrimaryKey(Integer menuId);

    int insert(Js02Menu record);

    int insertSelective(Js02Menu record);

    Js02Menu selectByPrimaryKey(Integer menuId);

    int updateByPrimaryKeySelective(Js02Menu record);

    int updateByPrimaryKey(Js02Menu record);

    Integer menuTotolSize();

    List<Js02Menu> selectAllMenu(@Param("offSet") Integer offSet, @Param("pageSize") Integer pageSize);

    Integer updateMenuStatus(Integer menuId);

    Integer insertMenuOne(Js02Menu js02Menu);

    List<Js02Menu> selectUserMenu(Integer roleId);
}