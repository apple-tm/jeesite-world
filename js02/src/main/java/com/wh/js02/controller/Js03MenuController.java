package com.wh.js02.controller;

import com.wh.js02.entity.Js03Menu;
import com.wh.js02.entity.ResultVo;
import com.wh.js02.req.PageDTO;
import com.wh.js02.service.Js03MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/menu")
public class Js03MenuController {

    @Autowired
    private Js03MenuService js03MenuService;

    @PostMapping("/insert")
    public ResultVo insert(@RequestBody Js03Menu js03Menu){
        return js03MenuService.insertMenu(js03Menu);
    }

    @PostMapping("/update")
    public ResultVo update(@RequestBody Js03Menu js03Menu){
        return js03MenuService.updateById(js03Menu);
    }

    @PostMapping("/delete")
    public ResultVo delete(@RequestBody Js03Menu js03Menu){
        return js03MenuService.deleteById(js03Menu);
    }

    @PostMapping("/select")
    public ResultVo select(){
        return js03MenuService.select();
    }

    @PostMapping("/fuzzy")
    public ResultVo fuzzy(@RequestBody PageDTO pageDTO){
        return js03MenuService.fuzzyQuery(pageDTO);
    }

}
