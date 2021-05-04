package com.wh.js02.controller;

import com.wh.js02.entity.Js03User;
import com.wh.js02.entity.ResultVo;
import com.wh.js02.req.PageDTO;
import com.wh.js02.service.Js03UserService;
import com.wh.js02.vo.PageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.List;

@Controller
@RequestMapping("/api/user")
public class Js03UserController {

    @Autowired
    private Js03UserService js03UserService;

    @PostMapping("/add")
    public @ResponseBody ResultVo insertUser(@RequestBody Js03User user){
        return js03UserService.insertUser(user);
    }

    @PostMapping("/delete")
    public @ResponseBody ResultVo deleteUser(@RequestBody Js03User user){
        return js03UserService.updateStatus(user);
    }

    @PostMapping("/update")
    public @ResponseBody ResultVo updateUser(@RequestBody Js03User user){
        return js03UserService.updateUserById(user);
    }

    @PostMapping("/query")
    public @ResponseBody ResultVo selectUser(@RequestBody Js03User user){
        return js03UserService.selectUserById(user);
    }

    @PostMapping("/pageQuery")
    public @ResponseBody
    ResultVo<PageVo<List<Js03User>>> pageQuery(@RequestBody PageDTO pageDTO){
        return js03UserService.pageQuery(pageDTO);
    }

    @PostMapping("/fuzzyQuery")
    public @ResponseBody
    ResultVo<PageVo<List<Js03User>>> fuzzyQuery(@RequestBody PageDTO pageDTO){
        return js03UserService.fuzzyQuery(pageDTO);
    }

    @PostMapping("/insertBatch")
    public @ResponseBody
    ResultVo batchInsert(MultipartFile file){
        return js03UserService.insertBatch(file);
    }

}
