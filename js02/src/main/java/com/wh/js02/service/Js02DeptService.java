package com.wh.js02.service;

import com.wh.js02.entity.Js02Dept;
import com.wh.js02.req.PageDTO;
import com.wh.js02.vo.PageVo;

import java.util.List;

public interface Js02DeptService {
    PageVo<List<Js02Dept>> getDeptList(PageDTO pageDTO);

    Integer addDept(Js02Dept js02Dept);

    Integer updateDept(Js02Dept js02Dept);

    Integer updateDeptStatus(Integer deptId);


}
