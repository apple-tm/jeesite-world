package com.example.js01.controller;

import ch.qos.logback.classic.pattern.SyslogStartConverter;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.example.js01.entity.ChangePassword;
import com.example.js01.entity.LoginReqDTO;
import com.example.js01.entity.RegisterUser;
import com.example.js01.service.RegisterUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
public class RegisterController {

    @Autowired
    private RegisterUserService registerService;

    // post：防止 get 方式明文传输导致用户信息泄露
    @PostMapping(value = "/register")
    @ResponseBody
    public String register(@RequestBody RegisterUser registerUser) {
        String regex = "^1[3|4|5|7|8][0-9]\\d{4,8}$";
        if (registerUser.getPhone().length() != 11) {
            return "手机号长度不为 11";
        } else {
            // 编译正则表达式
            Pattern pattern = Pattern.compile(regex);
            // 获取实现了表达式规则的匹配器
            Matcher matcher = pattern.matcher(registerUser.getPhone());
            // 执行匹配
            boolean matcherResult = matcher.matches();
            if (matcherResult) {
                // 校验密码是否一致
                if (registerUser.getPassword().equals(registerUser.getRePassword())) {
                    // 注册操作
                    Integer count = registerService.registerUser(registerUser);
                    System.out.println(count);
                    return count>0 ? count.toString() : "注册失败";
                } else {
                    return "两次输入的密码不一致";
                }
            } else {
                return "手机号格式错误";
            }
        }
    }

    @GetMapping(value = "/registerTest")
    @ResponseBody
    public String registerTest() {
        return registerService.registerManyUser(new ArrayList<>()).toString();
    }

    @PostMapping(value = "/delete/one")
    @ResponseBody
    public String deleteOneUser(@RequestBody RegisterUser registerUser) {
        return registerService.deleteOneUser(registerUser).toString().equals("1") ? "删除成功" : "删除失败";
    }

    @PostMapping(value = "/delete/many")
    @ResponseBody
    public String deleteManyUser(@RequestBody List<RegisterUser> registerUserList) {
        Integer count = registerService.deleteManyUser(registerUserList);
        return count.equals(registerUserList.size())
                ? new StringBuilder().append("成功删除条数：").append(count).toString()
                : "删除失败";
    }

    @PostMapping(value = "/update/one")
    @ResponseBody
    public String updateOneUser(@RequestBody RegisterUser registerUser) {
        return registerService.updateOneUser(registerUser).toString().equals("1") ? "修改成功" : "修改失败";
    }

    @GetMapping(value = "/query/one")
    @ResponseBody
    // @RequestParam可以映射 URL 参数， 不使用直接用参数名也可以映射
    public String queryOneUser(String phone) {
        try {
            List<RegisterUser> result = registerService.queryOneUser(phone);
            return JSON.toJSONString((result == null || result.size() == 0) ? "用户不存在或者电话错误" : result.get(0));
        } catch (Exception e) {
            e.printStackTrace();
            return JSON.toJSONString(e.getMessage());
        }
    }

    @PostMapping(value = "/login")
    @ResponseBody
    // @RequestParam可以映射 URL 参数， 不使用直接用参数名也可以映射
    public String login(@RequestBody LoginReqDTO loginReqDTO) {
        try {
            List<RegisterUser> result = registerService.login(loginReqDTO);
            return JSON.toJSONString((result == null || result.size()==0) ? "电话或密码错误" : result.get(0));
        } catch (Exception e) {
            e.printStackTrace();
            return JSON.toJSONString(e.getMessage());
        }
    }

    @PostMapping(value = "/page/query")
    @ResponseBody
    // @RequestParam可以映射 URL 参数， 不使用直接用参数名也可以映射
    public String pageQuery(@RequestBody RegisterUser registerUser) {
        try {
            List<RegisterUser> result = registerService.pageQuery(registerUser);
            return JSON.toJSONString((result == null || result.size()==0) ? "未查找到合适数据" : result);
        } catch (Exception e) {
            e.printStackTrace();
            return JSON.toJSONString(e.getMessage());
        }
    }


    @PostMapping(value = "/changePassword")
    @ResponseBody
    // @RequestParam可以映射 URL 参数， 不使用直接用参数名也可以映射
    public String changePassword(@RequestBody ChangePassword changePassword) {
        return registerService.changePassword(changePassword);
    }

}
