package com.wh.js02.controller;

import com.wh.js02.entity.Test;
import com.wh.js02.mapper.TestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

@Controller
public class TestController {

    @Autowired
    private TestMapper testMapper;

    @PostMapping(value = "/test")
    public @ResponseBody String testController(@RequestBody Test test){

//        test.setCreatedBy("磊磊");
//        test.setCreatedTime(new Date());
//        test.setUpdatedTime(new Date());
//        test.setStatus("1");
//        test.setUpdatedBy("磊");
//        Integer i = testMapper.testInsertOne(test);
//        return i > 0 ? "插入成功":"fail";
        return null;
    }


    @GetMapping(value = "/index")
    public String index(){
        return "index";
    }


}
