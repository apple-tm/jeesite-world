package com.wh.js02.controller;

import com.wh.js02.entity.Js02Dept;
import com.wh.js02.entity.ResultVo;
import com.wh.js02.entity.UserEntity;
import com.wh.js02.mapper.Js02DeptMapper;
import com.wh.js02.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static com.wh.js02.constant.URLConstant.LOGIN_PAGE;

@Controller
public class LoginController {

    @Autowired
    private LoginService loginService;

    @GetMapping(LOGIN_PAGE)
    public String loginPage(){
        return "login";
    }



    @PostMapping("/login")
    public String login(@RequestBody UserEntity userEntity){
        return loginService.login(userEntity);
    }

    @GetMapping("/dept/list")
    public @ResponseBody ResultVo<Js02Dept> deptList(@RequestParam String deptId) {
        ResultVo<Js02Dept> resultVo = new ResultVo<>();
        List<Js02Dept> deptList =  js02DeptMapper.selectAll();

        List<Js02Dept> parentList = new ArrayList<>();
        selectParent(parentList, deptId, deptList);

        Js02Dept js02Dept = new Js02Dept();
        for (Js02Dept item : parentList) {
            if (item.getDeptId().equals(Integer.valueOf(deptId))) {
                js02Dept = item;
            }
        }

        selectTreeParent(js02Dept, js02Dept.getDeptId(), deptList);

        resultVo.success();
        resultVo.setBody(js02Dept);
        return resultVo;
    }

    private void selectTreeParent(Js02Dept js02Dept, Integer deptId, List<Js02Dept> parentList) {
        for (Js02Dept item : parentList) {
            if (item.getDeptParentId().equals(deptId)) {
                if (null == js02Dept.getDeptList())
                    js02Dept.setDeptList(new ArrayList<>());
                js02Dept.getDeptList().add(item);
                selectTreeParent(item,item.getDeptId(), parentList);
            }
        }
    }

    private void selectParent(List<Js02Dept> parentList, String deptId,List<Js02Dept> deptList) {
        for (Js02Dept item : deptList) {
            if (item.getDeptId().equals(Integer.valueOf(deptId))) {
                parentList.add(item);
                if (item.getDeptParentId().equals(0)) return;
                selectParent(parentList, String.valueOf(item.getDeptParentId()), deptList);
            }
        }
    }



    @Autowired
    private Js02DeptMapper js02DeptMapper;
}
