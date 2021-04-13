package com.wh.js02.controller;

import com.wh.js02.entity.Js02Role;
import com.wh.js02.entity.ResultVo;
import com.wh.js02.req.PageDTO;
import com.wh.js02.service.Js02RoleService;
import com.wh.js02.vo.PageVo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.concurrent.TransferQueue;

@Controller
public class Js02RoleController {

    @Autowired
    private Js02RoleService js02RoleService;

    private final static Logger log = LoggerFactory.getLogger(Js02RoleController.class);

    @PostMapping("/roleList")
    public @ResponseBody
    ResultVo<Js02Role> getRoleList(@RequestBody PageDTO pageDTO){

        ResultVo resultVo = new ResultVo<>();
        try {
            PageVo<List<Js02Role>> roleList = js02RoleService.getRoleList(pageDTO);
            resultVo.setBody(roleList);
            if (roleList.getListBody() != null){
                resultVo.success();
            }else {
                throw new Exception();

            }
        }catch (Exception e){
            resultVo.fail("查询角色失败");
//            e.printStackTrace();
            log.error("查询角色失败", e);
        }finally {

        }
        return resultVo;
    }


    @PostMapping("/addRole")
    @ResponseBody
    public ResultVo addRole(@RequestBody Js02Role js02Role){

        ResultVo resultVo = new ResultVo<>();
        try {
            Integer i = js02RoleService.addRole(js02Role);
            if (i > 0) {
                resultVo.success();
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
            resultVo.fail("添加角色错误");
            e.printStackTrace();
        } finally {
            return resultVo;
        }
    }


    @PostMapping("/updateRole")
    public @ResponseBody ResultVo updateRole(@RequestBody Js02Role js02Role){
        ResultVo resultVo = new ResultVo();
        try {
            Integer i = js02RoleService.updateRole(js02Role);
            if (i > 0){
                resultVo.success();
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
            resultVo.fail("修改角色错误");
            e.printStackTrace();
        } finally {

        }

        return resultVo;
    }


    @PostMapping("/deleteRole")
    public @ResponseBody ResultVo deleteRole(@RequestBody Integer roleId){

        ResultVo resultVo = new ResultVo();
        try {
            Integer i = js02RoleService.updateRoleStatus(roleId);
            if (i > 0 ){
                resultVo.success();
            }else {
                throw new Exception();
            }
        }catch (Exception e){
            resultVo.fail("删除角色失败");
            e.printStackTrace();
        }finally {

        }
        return resultVo;
    }


}
