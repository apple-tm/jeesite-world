package com.wh.js02.service;

import com.wh.js02.entity.Js03Role;
import com.wh.js02.entity.ResultVo;
import com.wh.js02.req.PageDTO;
import com.wh.js02.vo.PageVo;

import java.util.List;

public interface Js03RoleService {
    ResultVo insertRole(Js03Role record);
    ResultVo updateById(Js03Role record);
    ResultVo deleteById(Js03Role js03Role);
    ResultVo select(PageDTO pageDTO);
    PageVo fuzzyQuery(PageDTO pageDTO);
}
