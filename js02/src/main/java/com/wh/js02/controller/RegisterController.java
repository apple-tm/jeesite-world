package com.wh.js02.controller;

import com.wh.js02.entity.ResultVo;
import com.wh.js02.entity.UserEntity;
import com.wh.js02.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class RegisterController {

    @Autowired
    private RegisterService registerService;

    @GetMapping("/registerPage")
    public String registerPage(){
        return "register";
    }

    @PostMapping("/register")
    public @ResponseBody ResultVo<Object> register(@RequestBody UserEntity userEntity){
        ResultVo<Object> resultVo = new ResultVo<>();

        if (userEntity.getPhoneNumber() == null
                || "".equals(userEntity.getPhoneNumber())
                || userEntity.getUserPassword() == null
                || "".equals(userEntity.getUserPassword())
                || !userEntity.getUserPassword().equals(userEntity.getUserRePassword())){
            resultVo.fail("输入错误");
        } else {
            Integer i = registerService.register(userEntity);
            if (i > 0){
                resultVo.success();
            }else {
                resultVo.fail("注册失败");
            }
        }

        return resultVo;
    }
}
