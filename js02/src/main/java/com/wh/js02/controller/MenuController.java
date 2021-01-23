package com.wh.js02.controller;

import com.wh.js02.entity.Js02Menu;
import com.wh.js02.entity.ResultVo;
import com.wh.js02.req.PageDTO;
import com.wh.js02.service.MenuService;
import com.wh.js02.vo.PageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class MenuController {

    @Autowired
    private MenuService menuService;

    @PostMapping("/menuList")
    public @ResponseBody
    ResultVo<List<Js02Menu>> getUserList(@RequestBody PageDTO pageDTO){
        ResultVo resultVo = new ResultVo();
        PageVo<List<Js02Menu>> menuPage = menuService.getMenuList(pageDTO);
        resultVo.setBody(menuPage);
        resultVo.success();
        return resultVo;
    }


    @PostMapping("/addMenu")
    @ResponseBody
    public ResultVo addMenu(@RequestBody Js02Menu js02Menu){

        ResultVo resultVo = new ResultVo<>();

        Integer i = menuService.addMenu(js02Menu);
        if (i > 0){
            resultVo.success();
        }

        return resultVo;
    }


    @PostMapping("/updateMenu")
    public @ResponseBody ResultVo updateMenu(@RequestBody Js02Menu js02Menu){
        ResultVo resultVo = new ResultVo();
        Integer i = menuService.updateMenu(js02Menu);
        if (i > 0){
            resultVo.success();
        }
        return resultVo;
    }


    @PostMapping("/deleteMenu")
    public @ResponseBody ResultVo deleteMenu(@RequestBody Integer menuId){
        ResultVo resultVo = new ResultVo();
        Integer i = menuService.updateMenuStatus(menuId);
        if (i > 0 ){
            resultVo.success();
        }else {
            resultVo.fail("删除菜单失败！");
        }
        return resultVo;
    }


}
