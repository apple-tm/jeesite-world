package com.wh.js02.service;

import com.wh.js02.entity.Js02Role;
import com.wh.js02.entity.ResultVo;
import com.wh.js02.req.PageDTO;
import com.wh.js02.vo.PageVo;

import java.util.List;

public interface Js02RoleService {
    PageVo<List<Js02Role>> getRoleList(PageDTO pageDTO) throws Exception;

    Integer updateRole(Js02Role js02Role) throws Exception;

    Integer updateRoleStatus(Integer roleId) throws Exception;

    Integer addRole(Js02Role js02Role) throws Exception;
}
