package com.wh.js02.controller;

import com.wh.js02.entity.Js03Log;
import com.wh.js02.entity.ResultVo;
import com.wh.js02.req.PageDTO;
import com.wh.js02.service.Js03LogService;
import com.wh.js02.vo.PageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/log")
public class Js03LogController {

    @Autowired
    private Js03LogService js03LogService;

    @PostMapping("/query")
    public ResultVo query(@RequestBody PageDTO pageDTO) {
        ResultVo resultVo = new ResultVo();
        PageVo pageVo = js03LogService.query(pageDTO);
        if (pageVo != null) {
            resultVo.setBody(pageVo);
            resultVo.success();
        } else {
            resultVo.fail("分页查询日志失败");
        }
        return resultVo;
    }

    @PostMapping("/delete")
    public ResultVo delete(@RequestBody Js03Log js03Log) {
        ResultVo resultVo = new ResultVo();
        int id = js03Log.getLogId();
        int count = js03LogService.deleteById(id);
        if (count == 1) {
            resultVo.success();
        } else {
            resultVo.fail("删除日志失败");
        }
        return resultVo;
    }

    @PostMapping("/fuzzy")
    public ResultVo fuzzy(@RequestBody PageDTO pageDTO) {
        ResultVo resultVo = new ResultVo();
        PageVo pageVo = js03LogService.fuzzyQuery(pageDTO);
        if (pageVo != null) {
            resultVo.setBody(pageVo);
            resultVo.success();
        } else {
            resultVo.fail("日志模糊查询失败");
        }
        return resultVo;
    }
}
