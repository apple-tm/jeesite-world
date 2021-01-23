package com.wh.js02.service.impl;

import com.wh.js02.entity.Js02Dept;
import com.wh.js02.mapper.Js02DeptMapper;
import com.wh.js02.req.PageDTO;
import com.wh.js02.service.Js02DeptService;
import com.wh.js02.vo.PageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Js02DeptServiceImpl implements Js02DeptService{

    @Autowired
    private Js02DeptMapper js02DeptMapper;

    @Override
    public PageVo<List<Js02Dept>> getDeptList(PageDTO pageDTO) {

        //机构总记录数
        Integer deptTotolSize = js02DeptMapper.deptTotolSize();
        //page页数
        Integer totolPage = deptTotolSize % pageDTO.getPageSize()==0 ? deptTotolSize / pageDTO.getPageSize() : deptTotolSize / pageDTO.getPageSize() + 1;
        //偏移量
        Integer offSet = (pageDTO.getPageNo()-1) * pageDTO.getPageSize();
        //分页查询
        List<Js02Dept> deptList = js02DeptMapper.selectAllDept(offSet,pageDTO.getPageSize());
        PageVo<List<Js02Dept>> pageVo = new PageVo<>();
        pageVo.setPageNo(pageDTO.getPageNo());
        pageVo.setPageSize(pageDTO.getPageSize());
        pageVo.setTotal(deptTotolSize);
        pageVo.setTotalPage(totolPage);
        pageVo.setListBody(deptList);

        return pageVo;
    }


    @Override
    public Integer addDept(Js02Dept js02Dept) {
        return js02DeptMapper.insertDeptOne(js02Dept);
    }

    @Override
    public Integer updateDept(Js02Dept js02Dept) {

        return js02DeptMapper.updateByPrimaryKeySelective(js02Dept);
    }

    @Override
    public Integer updateDeptStatus(Integer deptId) {
        return js02DeptMapper.updateDeptStatus(deptId);
    }
}
