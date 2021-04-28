package com.wh.js02.service.impl;

import com.wh.js02.entity.Js03Menu;
import com.wh.js02.entity.Js03Role;
import com.wh.js02.entity.Js03RoleMenu;
import com.wh.js02.entity.ResultVo;
import com.wh.js02.mapper.Js03RoleMapper;
import com.wh.js02.mapper.Js03RoleMenuMapper;
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
    private Js03RoleMenuMapper js03RoleMenuMapper;

    // 事务控制：插入角色和插入角色菜单中间表必须同时成功或者同时失败
    @Transactional
    @Override
    public ResultVo insertRole(Js03Role role) {
        ResultVo resultVo = new ResultVo();
        // 插入角色，返回ID,角色菜单中间表使用的role_code，前端传递，所以无需返回自增ID
        int id = js03RoleMapper.insertRole(role);
        if (id > 0){
            //组装中间表集合
            //获取角色里的菜单集合
            List<Js03Menu> menuList  = role.getJs03MenuList();
            //批量插入时用的中间表集合
            List<Js03RoleMenu> roleMenus = new ArrayList<>(menuList.size());
            // 使用角色ID和菜单ID组装中间表对象集合
            for (Js03Menu menu: menuList) {
                Js03RoleMenu js03RoleMenu = new Js03RoleMenu();
                js03RoleMenu.setRoleId(role.getId());
                js03RoleMenu.setMenuId(menu.getId());
                js03RoleMenu.setCreateBy("张三");
                js03RoleMenu.setCreateTime(new Date());
                js03RoleMenu.setUpdateBy("张三");
                js03RoleMenu.setUpdateTime(new Date());
                // 加入中间表集合
                roleMenus.add(js03RoleMenu);
            }
            // 批量插入role_menu中间表
            int count = js03RoleMenuMapper.insertBatch(roleMenus);
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
    @Transactional
    public ResultVo updateById(Js03Role role) {
        //修改角色前，先通过角色id删除角色与菜单的中间表
        int count1 = js03RoleMenuMapper.deleteRoleMenu(role.getId());
        ResultVo resultVo = new ResultVo();
        if (count1 > 0){
            //删除中间表成功后，再修改角色
            int count2 = js03RoleMapper.updateById(role);
                //组装插入中间表的集合
                List<Js03Menu> js03Menus = role.getJs03MenuList();
                List<Js03RoleMenu> roleMenuList = new ArrayList<>(js03Menus.size());
                for (Js03Menu js03Menu:js03Menus){
                    Js03RoleMenu js03RoleMenu = new Js03RoleMenu();
                    js03RoleMenu.setRoleId(role.getId());
                    js03RoleMenu.setMenuId(js03Menu.getId());
                    js03RoleMenu.setCreateBy("张三");
                    js03RoleMenu.setCreateTime(new Date());
                    js03RoleMenu.setStatus(1);
                    js03RoleMenu.setUpdateBy("张三");
                    js03RoleMenu.setUpdateTime(new Date());
                    roleMenuList.add(js03RoleMenu);
                }
                int batchCount = js03RoleMenuMapper.insertBatch(roleMenuList);
                if (batchCount > 0){
                    resultVo.success();
                } else {
                    resultVo.fail("修改角色后，插入中间表失败");
                }

        }
        return resultVo;
    }

    @Transactional
    @Override
    public ResultVo deleteById(Js03Role role) {
        //删除角色前，先删除中间表
        int count1 = js03RoleMenuMapper.deleteRoleMenu(role.getId());
        ResultVo resultVo = new ResultVo();
        if (count1 > 0) {
            //中间表删除成功后，再删除角色
            int count = js03RoleMapper.deleteById(role.getId());
            if (count == 1){
                resultVo.success();
            } else {
                resultVo.fail("删除角色失败");
            }
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
