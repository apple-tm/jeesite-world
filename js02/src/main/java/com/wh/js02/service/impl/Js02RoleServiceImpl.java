package com.wh.js02.service.impl;

import com.wh.js02.entity.Js02Menu;
import com.wh.js02.entity.Js02Role;
import com.wh.js02.entity.ResultVo;
import com.wh.js02.mapper.Js02RoleMapper;
import com.wh.js02.req.PageDTO;
import com.wh.js02.service.Js02RoleService;
import com.wh.js02.vo.PageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Js02RoleServiceImpl implements Js02RoleService {

    @Autowired
    private Js02RoleMapper js02RoleMapper;

    @Override
    public PageVo<List<Js02Role>> getRoleList(PageDTO pageDTO) {

        Integer roleTotolSize = js02RoleMapper.roleTotolSize();
        //page页数
        Integer totolPage = roleTotolSize % pageDTO.getPageSize()==0 ? roleTotolSize / pageDTO.getPageSize() : roleTotolSize / pageDTO.getPageSize() + 1;
        //偏移量
        Integer offSet = (pageDTO.getPageNo()-1) * pageDTO.getPageSize();
        //分页查询
        List<Js02Role> deptList = js02RoleMapper.selectAllRole(offSet,pageDTO.getPageSize());
        PageVo<List<Js02Role>> pageVo = new PageVo<>();
        pageVo.setPageNo(pageDTO.getPageNo());
        pageVo.setPageSize(pageDTO.getPageSize());
        pageVo.setTotal(roleTotolSize);
        pageVo.setTotalPage(totolPage);
        pageVo.setListBody(deptList);

        return pageVo;
    }


    @Override
    public Integer addRole(Js02Role js02Role) {
        // 1.插入角色的id返回回来
        // 2.插入中间表角色菜单中间表
        Integer i = js02RoleMapper.insertRoleOne(js02Role);
        List<Js02Menu> js02MenuList = js02Role.getJs02MenuList();

        Integer sumCount = 0;
        if (js02Role.getRoleId() != null) {
            for (Js02Menu js02Menu:js02MenuList){
                Integer count = js02RoleMapper.insertRoleAndMenu(js02Role.getRoleId(),js02Menu.getMenuId());
                if (count == 1) sumCount++;
            }
        }


        if (i.equals(1) && sumCount.equals(js02MenuList.size())) {
            // 成功
            return 1;
        } else {
            return -1;
        }
    }

    @Override
    public Integer updateRole(Js02Role js02Role) {

        Integer cnt1 = js02RoleMapper.deleteByRoleId(js02Role.getRoleId());
        Integer cnt2 = js02RoleMapper.updateByPrimaryKeySelective(js02Role);
        List<Js02Menu> js02Menus = js02Role.getJs02MenuList();
        Integer sumCnt = 0;
        if (js02Role.getRoleId()!=null && js02Role.getJs02MenuList()!=null) {
            for (Js02Menu js02Menu : js02Menus) {
                Integer cnt = js02RoleMapper.insertRoleAndMenu(js02Role.getRoleId(), js02Menu.getMenuId());
                if (cnt == 1) sumCnt++;
            }
        }
        if (cnt1.equals(1) && cnt2.equals(1) && sumCnt.equals(js02Menus.size())){
            return 1;
        }else {
            return -1;
        }


    }

    @Override
    public Integer updateRoleStatus(Integer roleId) {
        return js02RoleMapper.updateRoleStatus(roleId);
    }
}
