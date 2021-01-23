package com.wh.js02.controller;

import com.wh.js02.entity.Js02User;
import com.wh.js02.entity.ResultVo;
import com.wh.js02.req.PageDTO;
import com.wh.js02.service.Js02UserService;
import com.wh.js02.vo.PageVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class Js02UserController {

    @Autowired
    private Js02UserService js02UserService;



    @PostMapping("/userLogin")
//    @ResponseBody
    public ModelAndView login(@RequestBody Js02User js02User){
        ResultVo resultVo = new ResultVo<>();

        if (js02User.getUserPhone() == null
                || "".equals(js02User.getUserPhone())
                || js02User.getUserPassword() == null
                || "".equals(js02User.getUserPassword())){
            resultVo.fail("输入有误！");

        }else {
            Js02User js02User1 = js02UserService.login(js02User);
            if (js02User1 != null){
                resultVo.setBody(js02User1);
                resultVo.success();
            }
        }


        return new ModelAndView("home");
    }




    @RequestMapping("/homePage")
    public String homePage(){
        return "home";
    }

    /*查询所有用户*/
    @PostMapping("/userList")
    public @ResponseBody
    ResultVo<List<Js02User>> getUserList(@RequestBody PageDTO pageDTO){
        ResultVo resultVo = new ResultVo();
        PageVo<List<Js02User>> userPage = js02UserService.getUserList(pageDTO);
        resultVo.setBody(userPage);
        resultVo.success();
        return resultVo;
    }

    /*新增用户*/
    @PostMapping("/addUser")
    @ResponseBody
    public ResultVo addUser(@RequestBody Js02User js02User){

        ResultVo resultVo = new ResultVo<>();
        if ("".equals(js02User.getUserName())
                || "".equals(js02User.getUserCode())
                || "".equals(js02User.getUserPhone())
                || "".equals(js02User.getUserEmail())
                || "".equals(js02User.getUserPassword())
                || "".equals(js02User.getReUserPassword())
                || "".equals(js02User.getCreatedBy())
                || "".equals(js02User.getUpdatedBy())
                || ! js02User.getUserPassword().equals(js02User.getReUserPassword())
                ){
            resultVo.fail("信息输入不完整或两次密码不一致！");
        }
        Integer i = js02UserService.addUser(js02User);
        if (i > 0){
            resultVo.success();
        }

        return resultVo;
    }


    /*模糊查询*/
    @PostMapping("/fuzzyQuery")
    public @ResponseBody ResultVo fuzzyQuery(@RequestBody PageDTO pageDTO){

        ResultVo resultVo = new ResultVo<>();

        PageVo<List<Js02User>> userList = js02UserService.fuzzyQuery(pageDTO);
        resultVo.setBody(userList);
        resultVo.success();
        return resultVo;
    }

    /*修改用户*/
    @PostMapping("/updateUser")
    public @ResponseBody ResultVo updateUser(@RequestBody Js02User js02User){
        ResultVo resultVo = new ResultVo();
        Integer i = js02UserService.updateUser(js02User);
        if (i > 0){
            resultVo.success();
        }
        return resultVo;
    }


    @PostMapping("/deleteUser")
    public @ResponseBody ResultVo deleteUser(@RequestBody Integer userId){
        ResultVo resultVo = new ResultVo();
        Integer i = js02UserService.updateUserStatus(userId);
        if (i > 0 ){
            resultVo.success();
        }else {
            resultVo.fail("删除用户失败！");
        }
        return resultVo;
    }


}
