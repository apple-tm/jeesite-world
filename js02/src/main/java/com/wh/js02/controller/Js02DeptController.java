package com.wh.js02.controller;

import com.wh.js02.entity.Js02Dept;
import com.wh.js02.entity.ResultVo;
import com.wh.js02.req.PageDTO;
import com.wh.js02.service.Js02DeptService;
import com.wh.js02.vo.PageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class Js02DeptController {

    @Autowired
    private Js02DeptService js02DeptService;

    @PostMapping("/deptList")
    public @ResponseBody
    ResultVo<Js02Dept> getDeptList(@RequestBody PageDTO pageDTO){

        ResultVo resultVo = new ResultVo<>();
        PageVo<List<Js02Dept>> deptList = js02DeptService.getDeptList(pageDTO);

        resultVo.setBody(deptList);
        resultVo.success();
        return resultVo;
    }


    @PostMapping("/addDept")
    @ResponseBody
    public ResultVo addDept(@RequestBody Js02Dept js02Dept){

        ResultVo resultVo = new ResultVo<>();

        Integer i = js02DeptService.addDept(js02Dept);
        if (i > 0){
            resultVo.success();
        }

        return resultVo;
    }


    @PostMapping("/updateDept")
    public @ResponseBody ResultVo updateDept(@RequestBody Js02Dept js02Dept){
        ResultVo resultVo = new ResultVo();
        Integer i = js02DeptService.updateDept(js02Dept);
        if (i > 0){
            resultVo.success();
        }
        return resultVo;
    }


    @PostMapping("/deleteDept")
    public @ResponseBody ResultVo deleteDept(@RequestBody Integer deptId){
        ResultVo resultVo = new ResultVo();
        Integer i = js02DeptService.updateDeptStatus(deptId);
        if (i > 0 ){
            resultVo.success();
        }else {
            resultVo.fail("删除机构失败！");
        }
        return resultVo;
    }


}
