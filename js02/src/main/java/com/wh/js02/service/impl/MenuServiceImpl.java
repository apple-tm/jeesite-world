package com.wh.js02.service.impl;

import com.wh.js02.entity.Js02Menu;
import com.wh.js02.mapper.Js02MenuMapper;
import com.wh.js02.req.PageDTO;
import com.wh.js02.service.MenuService;
import com.wh.js02.vo.PageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private Js02MenuMapper js02MenuMapper;

    @Override
    public PageVo<List<Js02Menu>> getMenuList(PageDTO pageDTO) {
        //菜单总记录数
        Integer menuTotolSize = js02MenuMapper.menuTotolSize();
        //page页数
        Integer totolPage = menuTotolSize % pageDTO.getPageSize()==0 ? menuTotolSize / pageDTO.getPageSize() : menuTotolSize / pageDTO.getPageSize() + 1;
        //偏移量
        Integer offSet = (pageDTO.getPageNo()-1) * pageDTO.getPageSize();
        //分页查询
        List<Js02Menu> deptList = js02MenuMapper.selectAllMenu(offSet,pageDTO.getPageSize());
        PageVo<List<Js02Menu>> pageVo = new PageVo<>();
        pageVo.setPageNo(pageDTO.getPageNo());
        pageVo.setPageSize(pageDTO.getPageSize());
        pageVo.setTotal(menuTotolSize);
        pageVo.setTotalPage(totolPage);
        pageVo.setListBody(deptList);

        return pageVo;
    }


    @Override
    public Integer addMenu(Js02Menu js02Menu) {
        return js02MenuMapper.insertMenuOne(js02Menu);
    }

    @Override
    public Integer updateMenu(Js02Menu js02Menu) {

        return js02MenuMapper.updateByPrimaryKeySelective(js02Menu);
    }

    @Override
    public Integer updateMenuStatus(Integer menuId) {
        return js02MenuMapper.updateMenuStatus(menuId);
    }
}
