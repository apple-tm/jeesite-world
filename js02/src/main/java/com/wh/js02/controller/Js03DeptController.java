package com.wh.js02.controller;

import com.wh.js02.entity.Js03Dept;
import com.wh.js02.entity.ResultVo;
import com.wh.js02.req.PageDTO;
import com.wh.js02.service.Js03DeptService;
import com.wh.js02.vo.PageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dept")
public class Js03DeptController {
    @Autowired
    private Js03DeptService js03DeptService;

    @PostMapping("/query")
    public ResultVo<Js03Dept> query(@RequestBody PageDTO pageDTO){
        return js03DeptService.selectAll(pageDTO);
    }

    @PostMapping("/update")
    public ResultVo update(@RequestBody Js03Dept js03Dept){
        return js03DeptService.updateById(js03Dept);
    }

    @PostMapping("/delete")
    public ResultVo delete(@RequestBody Js03Dept js03Dept){
        return js03DeptService.deleteById(js03Dept);
    }

    @PostMapping("/insert")
    public ResultVo insert(@RequestBody Js03Dept js03Dept){
        return js03DeptService.insertDept(js03Dept);
    }

    @PostMapping("/fuzzyQuery")
    public ResultVo fuzzyQuery(@RequestBody PageDTO pageDTO){
        ResultVo resultVo = new ResultVo();
        PageVo pageVo = js03DeptService.fuzzyQuery(pageDTO);
        if (pageVo.getListBody() != null){
            resultVo.setBody(pageVo);
            resultVo.success();
        } else {
            resultVo.fail("没有相似机构");
        }
        return resultVo;

    }
}
