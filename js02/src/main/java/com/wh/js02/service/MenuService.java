package com.wh.js02.service;

import com.wh.js02.entity.Js02Menu;
import com.wh.js02.req.PageDTO;
import com.wh.js02.vo.PageVo;

import java.util.List;

public interface MenuService {
    PageVo<List<Js02Menu>> getMenuList(PageDTO pageDTO);

    Integer updateMenu(Js02Menu js02Menu);

    Integer updateMenuStatus(Integer menuId);

    Integer addMenu(Js02Menu js02Menu);
}

