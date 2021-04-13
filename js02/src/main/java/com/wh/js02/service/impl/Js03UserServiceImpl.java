package com.wh.js02.service.impl;

import com.wh.js02.entity.Js03Dept;
import com.wh.js02.entity.Js03Role;
import com.wh.js02.entity.Js03User;
import com.wh.js02.entity.ResultVo;
import com.wh.js02.mapper.Js03DeptMapper;
import com.wh.js02.mapper.Js03RoleMapper;
import com.wh.js02.mapper.Js03UserMapper;
import com.wh.js02.req.PageDTO;
import com.wh.js02.service.Js03UserService;
import com.wh.js02.vo.PageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Js03UserServiceImpl implements Js03UserService {

    @Autowired
    private Js03UserMapper js03UserMapper;
    @Autowired
    private Js03RoleMapper js03RoleMapper;
    @Autowired
    private Js03DeptMapper js03DeptMapper;


    @Override
    public ResultVo userLogin(Js03User user) {
        Js03User js03User = js03UserMapper.userLogin(user);
        ResultVo resultVo = new ResultVo();

        if (js03User != null){
            if (!user.getPassword().equals(js03User.getPassword())){
                resultVo.fail("密码错误");
            } else {
                resultVo.success();
            }
        } else {
            resultVo.fail("用户不存在，请注册");
        }
        return resultVo;
    }

    @Override
    public ResultVo<PageVo<List<Js03User>>> pageQuery(PageDTO pageDTO) {
        //分页    limit offset num
        int offset = (pageDTO.getPageNo() - 1) * pageDTO.getPageSize();
        int number = pageDTO.getPageSize();
        //当前页用户
        List<Js03User> userList = js03UserMapper.pageQuery(offset,number);
        //状态为1的正常用户数（总记录数）
        int totalUser = js03UserMapper.queryUserNum();
        //总页数
        int totalPageNum = totalUser % number == 0 ? totalUser / number : totalUser / number + 1;

        ResultVo resultVo = new ResultVo();
        PageVo<List<Js03User>> pageVo = new PageVo<>();
        pageVo.setPageNo(pageDTO.getPageNo());
        pageVo.setPageSize(pageDTO.getPageSize());
        pageVo.setTotal(totalUser);
        pageVo.setTotalPage(totalPageNum);
        pageVo.setListBody(userList);
        if (userList != null){
            resultVo.success();
            resultVo.setBody(pageVo);
        } else {
            resultVo.fail("请求失败");
        }
        return resultVo;
    }

    @Override
    public ResultVo insertUser(Js03User user) {
        int count = js03UserMapper.insert(user);
        ResultVo resultVo = new ResultVo();

        if (count == 1){
            resultVo.success();
        } else {
            resultVo.fail("添加用户失败");
        }

        return resultVo;
    }




    @Override
    public ResultVo updateStatus(Js03User user) {
        int count = js03UserMapper.updateStatus(user);
        ResultVo resultVo = new ResultVo();

        if (count == 1){
            resultVo.success();
        } else {
            resultVo.fail("修改用户失败");
        }

        return resultVo;
    }

    @Override
    public ResultVo updateUserById(Js03User user) {
        int count = js03UserMapper.updateByPrimaryKey(user);
        ResultVo resultVo = new ResultVo();

        if (count == 1){
            resultVo.success();
        } else {
            resultVo.fail("修改用户失败");
        }

        return resultVo;
    }

    @Override
    public ResultVo selectUserById(Js03User user) {
        return null;
    }
}
