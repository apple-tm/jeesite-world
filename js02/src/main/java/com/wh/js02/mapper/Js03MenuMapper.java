package com.wh.js02.mapper;

import com.wh.js02.entity.Js03Menu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface Js03MenuMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Js03Menu record);

    int insertSelective(Js03Menu record);

    Js03Menu selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Js03Menu record);

    int updateByPrimaryKey(Js03Menu record);

    /**
     * 新增菜单
     * @param record
     * @return
     */
    int insertMenu(Js03Menu record);

    /**
     * 通过id修改菜单
     * @param record
     * @return
     */
    int updateById(Js03Menu record);

    /**
     * 删除菜单（修改状态为-1）
     * @param id
     * @return
     */
    int deleteById(Integer id);

    /**
     * 查询菜单列表
     * @return
     */
    List<Js03Menu> select();

    List<Js03Menu> fuzzyQuery(@Param("offset") int offset,@Param("pageSize") int pageSize,@Param("searchKey") String searchKey);
}