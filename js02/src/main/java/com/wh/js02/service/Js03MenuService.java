package com.wh.js02.service;

import com.wh.js02.entity.Js03Menu;
import com.wh.js02.entity.ResultVo;
import com.wh.js02.req.PageDTO;

import java.util.List;

public interface Js03MenuService {
    /**
     * 新增菜单
     * @param record
     * @return
     */
    ResultVo insertMenu(Js03Menu record);

    /**
     * 通过id修改菜单
     * @param record
     * @return
     */
    ResultVo updateById(Js03Menu record);

    /**
     * 删除菜单（修改状态为-1）
     * @param id
     * @return
     */
    ResultVo deleteById(Js03Menu js03Menu);

    /**
     * 查询菜单列表
     * @return
     */
    ResultVo select();

    ResultVo fuzzyQuery(PageDTO pageDTO);
}
