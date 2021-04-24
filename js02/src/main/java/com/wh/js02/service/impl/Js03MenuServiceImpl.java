package com.wh.js02.service.impl;

import com.wh.js02.entity.Js03Menu;
import com.wh.js02.entity.ResultVo;
import com.wh.js02.mapper.Js03MenuMapper;
import com.wh.js02.req.PageDTO;
import com.wh.js02.service.Js03MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class Js03MenuServiceImpl implements Js03MenuService {

    @Autowired
    private Js03MenuMapper js03MenuMapper;

    @Override
    public ResultVo insertMenu(Js03Menu record) {
        int count = js03MenuMapper.insertMenu(record);
        ResultVo resultVo = new ResultVo();
        if (count == 1){
            resultVo.success();
        } else {
            resultVo.fail("新增菜单失败");
        }
        return resultVo;
    }

    @Override
    public ResultVo updateById(Js03Menu record) {
        int count = js03MenuMapper.updateById(record);
        ResultVo resultVo = new ResultVo();
        if (count == 1){
            resultVo.success();
        } else {
            resultVo.fail("修改菜单失败");
        }
        return resultVo;
    }

    @Override
    public ResultVo deleteById(Js03Menu js03Menu) {
        int id = js03Menu.getId();
        int count = js03MenuMapper.deleteById(id);
        ResultVo resultVo = new ResultVo();
        if (count == 1){
            resultVo.success();
        } else {
            resultVo.fail("删除菜单失败");
        }
        return resultVo;
    }

    @Override
    public ResultVo select() {
        List<Js03Menu> list = js03MenuMapper.select();
        ResultVo resultVo = new ResultVo();
        if (list.size() > 0){
            resultVo.setBody(list);
            resultVo.success();
        } else {
            resultVo.fail("查询菜单表失败");
        }
        return resultVo;
    }

    @Override
    public ResultVo fuzzyQuery(PageDTO pageDTO) {
        ResultVo resultVo = new ResultVo();
        int pageSize = pageDTO.getPageSize();
        int offset = (pageDTO.getPageNo()-1)*pageSize;
        String searchKey = pageDTO.getSearchKey();
        List<Js03Menu> list = js03MenuMapper.fuzzyQuery(offset,pageSize,searchKey);
        if (list.size() > 0){
            resultVo.success();
            resultVo.setBody(list);
        } else {
            resultVo.fail("模糊查询菜单失败");
        }
        return resultVo;
    }
}
