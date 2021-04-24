package com.wh.js02.mapper;

import com.wh.js02.entity.Js03RolePermission;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface Js03RolePermissionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Js03RolePermission record);

    /**
     * 批量插入
     *
     * @param list
     * @return
     */
    int insertBatch(List<Js03RolePermission> list);

    int insertSelective(Js03RolePermission record);

    Js03RolePermission selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Js03RolePermission record);

    int updateByPrimaryKey(Js03RolePermission record);
}