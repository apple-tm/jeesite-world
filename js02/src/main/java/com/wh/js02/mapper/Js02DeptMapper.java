package com.wh.js02.mapper;

import com.wh.js02.entity.Js02Dept;
import com.wh.js02.entity.Js02User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface Js02DeptMapper {
    int deleteByPrimaryKey(Integer deptId);

    int insert(Js02Dept record);

    int insertSelective(Js02Dept record);

    Js02Dept selectByPrimaryKey(Integer deptId);

    int updateByPrimaryKeySelective(Js02Dept record);

    int updateByPrimaryKey(Js02Dept record);

    List<Js02Dept> selectAll();

    Js02Dept selectDept(Integer deptId);

    Integer deptTotolSize();

    List<Js02Dept> selectAllDept(@Param("offSet") Integer offSet,@Param("pageSize") Integer pageSize);

    Integer updateDeptStatus(Integer deptId);

    Integer insertDeptOne(Js02Dept js02Dept);


}