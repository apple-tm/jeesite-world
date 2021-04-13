package com.wh.js02.controller;

import com.wh.js02.annotation.Exclude;
import com.wh.js02.entity.Test;
import com.wh.js02.mapper.TestMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

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

    private Logger logger = LoggerFactory.getLogger(TestController.class);

    @GetMapping(value = "/api/login")
    @ResponseBody
    public String testLoggerTag() {
        long startTime = System.currentTimeMillis();
        String username="wuer";
        try {
            long o = 99999999999999999L/12L;
        }catch (ArithmeticException e){
//            e.printStackTrace();
            logger.error("登录错误",e);
        } finally {
            logger.info("{}登录成功：",username);

        }
        logger.info("登录接口成功耗时：{}ms", System.currentTimeMillis() - startTime);
        return "test";
    }




}
