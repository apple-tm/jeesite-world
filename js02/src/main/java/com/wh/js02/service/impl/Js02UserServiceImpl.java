package com.wh.js02.service.impl;

import com.wh.js02.entity.Js02Dept;
import com.wh.js02.entity.Js02Menu;
import com.wh.js02.entity.Js02Role;
import com.wh.js02.entity.Js02User;
import com.wh.js02.mapper.Js02DeptMapper;
import com.wh.js02.mapper.Js02MenuMapper;
import com.wh.js02.mapper.Js02RoleMapper;
import com.wh.js02.mapper.Js02UserMapper;
import com.wh.js02.req.PageDTO;
import com.wh.js02.service.Js02UserService;
import com.wh.js02.vo.PageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

@Service
public class Js02UserServiceImpl implements Js02UserService{

    @Autowired
    private Js02UserMapper js02UserMapper;
    @Autowired
    private Js02RoleMapper js02RoleMapper;
    @Autowired
    private Js02DeptMapper js02DeptMapper;
    @Autowired
    private Js02MenuMapper js02MenuMapper;


    @Override
    public Js02User login(Js02User js02User) {

        Js02User js02User1 = js02UserMapper.login(js02User);

        return js02User1;
    }

    @Override
    public PageVo<List<Js02User>> getUserList(PageDTO pageDTO) {
        // 分页　limit 偏移量，数量
        int offset = (pageDTO.getPageNo() - 1) * pageDTO.getPageSize();

        List<Js02User> userList = js02UserMapper.selectAllUser(offset, pageDTO.getPageSize());

        // 查询总的有效用户数量
        Integer totalSize = js02UserMapper.totolSize();
        // 总页数 = 总的记录数/每页数量
        Integer totalPage = totalSize % pageDTO.getPageSize() == 0 ? totalSize/pageDTO.getPageSize() : totalSize/pageDTO.getPageSize() + 1;

        for (Js02User user:userList){
            Js02Role js02Role = js02RoleMapper.selectRole(user.getUserRoleId());
            Js02Dept js02Dept = js02DeptMapper.selectDept(user.getUserDeptId());
            user.setJs02Role(js02Role);
            user.setJs02Dept(js02Dept);
        }
        PageVo<List<Js02User>> pageVo = new PageVo<>();
        pageVo.setPageNo(pageDTO.getPageNo());
        pageVo.setPageSize(pageDTO.getPageSize());
        pageVo.setTotal(totalSize);
        pageVo.setTotalPage(totalPage);
        pageVo.setListBody(userList);

        return pageVo;
    }

    @Override
    public Integer addUser(Js02User js02User) {
        return js02UserMapper.insertUserOne(js02User);
    }

    @Override
    public PageVo<List<Js02User>> fuzzyQuery(PageDTO pageDTO) {

        // 分页　limit 偏移量，数量
        int offset = (pageDTO.getPageNo() - 1) * pageDTO.getPageSize();


        // 模糊查询总的有效用户数量
        Integer totalSize = js02UserMapper.totolSizeFuzzy(pageDTO.getSearchKey());
        // 总页数 = 总的记录数/每页数量
        Integer totalPage = totalSize % pageDTO.getPageSize() == 0 ? totalSize/pageDTO.getPageSize() : totalSize/pageDTO.getPageSize() + 1;
        // 模糊查询
        List<Js02User> userList = js02UserMapper.fuzzyQuery(offset,pageDTO.getPageSize(),pageDTO.getSearchKey());

        for (Js02User user:userList){
            Js02Role js02Role = js02RoleMapper.selectRole(user.getUserRoleId());
            Js02Dept js02Dept = js02DeptMapper.selectDept(user.getUserDeptId());
            user.setJs02Role(js02Role);
            user.setJs02Dept(js02Dept);
        }
        PageVo<List<Js02User>> pageVo = new PageVo<>();
        pageVo.setPageNo(pageDTO.getPageNo());
        pageVo.setPageSize(pageDTO.getPageSize());
        pageVo.setTotal(totalSize);
        pageVo.setTotalPage(totalPage);
        pageVo.setListBody(userList);

        return pageVo;
    }

    @Override
    public Integer updateUser(Js02User js02User) {
        return js02UserMapper.updateByPrimaryKeySelective(js02User);
    }

    @Override
    public Integer updateUserStatus(Integer userId) {
        return js02UserMapper.updateUserStatus(userId);
    }
}
