package com.wh.js02.mapper;

import com.wh.js02.entity.Js03RoleMenu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface Js03RoleMenuMapper {
    int insert(Js03RoleMenu record);

    int insertSelective(Js03RoleMenu record);

    int insertBatch(List<Js03RoleMenu> list);

    int deleteRoleMenu(int roleId);
}