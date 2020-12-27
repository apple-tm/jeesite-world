package com.example.js01.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {

    @GetMapping(value = "/test/123", produces = "application/json")
    @ResponseBody
    public String test() {
        // 序列化 String 成 JSON，以便浏览器解析
        // JSON.toJSONString("hello jeesite world!!!")
        return "JSON.toJSONString(\"hello jeesite world!!!\")";
    }
}
