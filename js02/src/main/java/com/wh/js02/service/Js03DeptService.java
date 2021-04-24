package com.wh.js02.service;

import com.wh.js02.entity.Js03Dept;
import com.wh.js02.entity.Js03User;
import com.wh.js02.entity.ResultVo;
import com.wh.js02.req.PageDTO;
import com.wh.js02.vo.PageVo;

import java.util.List;

public interface Js03DeptService {
    ResultVo<Js03Dept> selectAll(PageDTO pageDTO);

    ResultVo updateById(Js03Dept js03Dept);

    ResultVo deleteById(Js03Dept js03Dept);

    ResultVo insertDept(Js03Dept js03Dept);

    PageVo fuzzyQuery(PageDTO pageDTO);
}
