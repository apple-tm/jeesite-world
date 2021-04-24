package com.wh.js02.service.impl;

import com.wh.js02.entity.Js03Dept;
import com.wh.js02.entity.Js03User;
import com.wh.js02.entity.ResultVo;
import com.wh.js02.mapper.Js03DeptMapper;
import com.wh.js02.req.PageDTO;
import com.wh.js02.service.Js03DeptService;
import com.wh.js02.vo.PageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Js03DeptServiceImpl implements Js03DeptService {

    @Autowired
    private Js03DeptMapper js03DeptMapper;

    @Override
    public ResultVo<Js03Dept> selectAll(PageDTO pageDTO) {

        List<Js03Dept> js03Depts = js03DeptMapper.selectAll(0,pageDTO.getPageSize());
        ResultVo resultVo = new ResultVo<>();
        if (js03Depts != null){
            resultVo.setBody(js03Depts);
            resultVo.success();
        } else {
            resultVo.fail("查询机构失败");
        }

        return resultVo;
    }

    @Override
    public ResultVo updateById(Js03Dept js03Dept) {
        int count = js03DeptMapper.updateById(js03Dept);
        ResultVo resultVo = new ResultVo();
        if (count == 1){
            resultVo.success();
        } else {
            resultVo.fail("修改机构失败");
        }
        return resultVo;
    }

    @Override
    public ResultVo deleteById(Js03Dept js03Dept) {
        int id = js03Dept.getId();
        int count = js03DeptMapper.deleteById(id);
        ResultVo resultVo = new ResultVo();
        if (count == 1){
            resultVo.success();
        } else {
            resultVo.fail("删除机构失败");
        }
        return resultVo;
    }

    @Override
    public ResultVo insertDept(Js03Dept js03Dept) {
        int count  = js03DeptMapper.insertDept(js03Dept);
        ResultVo resultVo = new ResultVo();
        if (count == 1){
            resultVo.success();
        } else {
            resultVo.fail("新增机构失败");
        }
        return resultVo;
    }

    @Override
    public PageVo fuzzyQuery(PageDTO pageDTO) {

        PageVo pageVo = new PageVo();

        int offset = (pageDTO.getPageNo()-1) * pageDTO.getPageSize();
        int pageSize = pageDTO.getPageSize();
        String searchKey = pageDTO.getSearchKey();
        List<Js03Dept> list = js03DeptMapper.fuzzyQuery(offset,pageSize,searchKey);
        if (list == null){
            return null;
        } else {
            int total = list.size();
            int totalPage = total % pageSize == 0 ? total / pageSize : total / pageSize + 1;
            pageVo.setTotal(total);
            pageDTO.setPageNo(pageDTO.getPageNo());
            pageVo.setTotalPage(totalPage);
            pageVo.setListBody(list);
            pageVo.setPageSize(pageSize);
        }
        return pageVo;
    }
}
