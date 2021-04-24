package com.wh.js02.controller;

import com.wh.js02.entity.Js03Menu;
import com.wh.js02.entity.Js03Role;
import com.wh.js02.entity.ResultVo;
import com.wh.js02.req.PageDTO;
import com.wh.js02.service.Js03RoleService;
import com.wh.js02.vo.PageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/role")
public class Js03RoleController {

    @Autowired
    private Js03RoleService js03RoleService;

    @PostMapping("/insert")
    public ResultVo insert(@RequestBody Js03Role js03Role){
        return js03RoleService.insertRole(js03Role);
    }

    @PostMapping("/update")
    public ResultVo update(@RequestBody Js03Role js03Role){
        return js03RoleService.updateById(js03Role);
    }

    @PostMapping("/delete")
    public ResultVo delete(@RequestBody Js03Role js03Role){
        return js03RoleService.deleteById(js03Role);
    }

    @PostMapping("/query")
    public ResultVo select(@RequestBody PageDTO pageDTO){
        return  js03RoleService.select(pageDTO);
    }

    @PostMapping("/fuzzy")
    public PageVo fuzzyQuery(@RequestBody PageDTO pageDTO){
        return js03RoleService.fuzzyQuery(pageDTO);
    }
}
