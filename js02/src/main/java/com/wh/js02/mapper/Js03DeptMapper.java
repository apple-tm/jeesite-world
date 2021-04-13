package com.wh.js02.mapper;

import com.wh.js02.entity.Js03Dept;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface Js03DeptMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Js03Dept record);

    int insertSelective(Js03Dept record);

    Js03Dept selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Js03Dept record);

    int updateByPrimaryKey(Js03Dept record);

    Js03Dept queryDeptByCode(String deptCode);
}