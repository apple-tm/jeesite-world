package com.wh.js02.service.impl;

import com.wh.js02.entity.Js03Menu;
import com.wh.js02.entity.Js03Role;
import com.wh.js02.entity.Js03RolePermission;
import com.wh.js02.entity.ResultVo;
import com.wh.js02.mapper.Js03RoleMapper;
import com.wh.js02.mapper.Js03RolePermissionMapper;
import com.wh.js02.req.PageDTO;
import com.wh.js02.service.Js03RoleService;
import com.wh.js02.vo.PageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Service
public class Js03RoleServiceImpl implements Js03RoleService {

    @Autowired
    private Js03RoleMapper js03RoleMapper;

    @Autowired
    private Js03RolePermissionMapper js03RolePermissionMapper;

    // 事务控制：插入角色和插入角色菜单中间表必须同时成功或者同时失败
    @Transactional
    @Override
    public ResultVo insertRole(Js03Role record) {
        ResultVo resultVo = new ResultVo();
        // 插入角色，返回ID,角色菜单中间表使用的role_code，前端传递，所以无需返回自增ID
        int id = js03RoleMapper.insertRole(record);
        if (id > 0){
            //角色对应菜单集合，含菜单code
            List<Js03Menu> menuList  = record.getJs03MenuList();

            // 使用角色code和菜单permissionValue组装Js03RolePermission中间表对象集合
            List<Js03RolePermission> rolePermissions = new ArrayList<>(menuList.size());
            for (Js03Menu menu: menuList) {
                Js03RolePermission js03RolePermission = new Js03RolePermission();
                js03RolePermission.setRoleCode(record.getRoleCode());
                js03RolePermission.setPermissionValue(menu.getPermissionValue());

                js03RolePermission.setCreateBy("zs");
                js03RolePermission.setCreateTime(new Date());
                js03RolePermission.setUpdateBy("zs");
                js03RolePermission.setUpdateTime(new Date());
                // 加入中间表集合
                rolePermissions.add(js03RolePermission);
            }
            // 批量插入Js03RolePermission中间表
            int count = js03RolePermissionMapper.insertBatch(rolePermissions);
            // 插入中间表的数量要等于前端选择的菜单数量
            if (count == menuList.size()){
                resultVo.success();
            } else {
                resultVo.fail("插入角色菜单的中间表失败");
            }
        } else {
            resultVo.fail("插入角色失败");
        }
        return resultVo;
    }

    @Override
    public ResultVo updateById(Js03Role record) {
        int count = js03RoleMapper.updateById(record);
        ResultVo resultVo = new ResultVo();
        if (count == 1){
            resultVo.success();
        } else {
            resultVo.fail("修改角色失败");
        }
        return resultVo;
    }

    @Override
    public ResultVo deleteById(Js03Role js03Role) {
        int id = js03Role.getId();
        int count = js03RoleMapper.deleteById(id);
        ResultVo resultVo = new ResultVo();
        if (count == 1){
            resultVo.success();
        } else {
            resultVo.fail("删除角色失败");
        }
        return resultVo;
    }

    @Override
    public ResultVo select(PageDTO pageDTO) {
        int pageSize=pageDTO.getPageSize();
        int offset = (pageDTO.getPageNo()-1)*pageSize;
        List<Js03Role> list = js03RoleMapper.select(offset,pageSize);
        ResultVo resultVo = new ResultVo();
        if (list.size() > 0) {
            resultVo.setBody(list);
            resultVo.success();
        } else {
            resultVo.fail("查询角色列表失败");
        }
        return resultVo;
    }

    @Override
    public PageVo fuzzyQuery(PageDTO pageDTO) {
        PageVo pageVo = new PageVo();

        int offset = (pageDTO.getPageNo()-1) * pageDTO.getPageSize();
        int pageSize = pageDTO.getPageSize();
        String searchKey = pageDTO.getSearchKey();
        List<Js03Role> list = js03RoleMapper.fuzzyQuery(offset,pageSize,searchKey);
        if (list == null){
            pageVo.setListBody("模糊查询角色失败");
        } else {
            int total = list.size();
            int totalPage = total % pageSize == 0 ? total / pageSize : total / pageSize + 1;
            pageDTO.setPageNo(pageDTO.getPageNo());
            pageVo.setTotal(total);
            pageVo.setTotalPage(totalPage);
            pageVo.setListBody(list);
            pageVo.setPageSize(pageSize);
        }
        return pageVo;
    }
}
