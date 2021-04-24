package com.wh.js02.mapper;

import com.wh.js02.entity.Js03Dept;
import com.wh.js02.req.PageDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface Js03DeptMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Js03Dept record);

    int insertSelective(Js03Dept record);

    Js03Dept selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Js03Dept record);

    int updateByPrimaryKey(Js03Dept record);

    Js03Dept queryDeptByCode(String deptCode);

    /**
     * 查询所有机构
     * @return
     */
    List<Js03Dept> selectAll(@Param("offset") int offset,@Param("pageSize") int pageSize);

    /**
     * 修改机构
     */
    int updateById(Js03Dept js03Dept);

    /**
     * 修改机构状态为-1（删除）
     * @param id
     * @return
     */
    int deleteById(Integer id);

    int insertDept(Js03Dept js03Dept);

    List<Js03Dept> fuzzyQuery(@Param("offset") Integer offset, @Param("pageSize") Integer pageSize,@Param("searchKey") String searchKey);

}