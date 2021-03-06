package com.wh.js02.service.impl;

import com.wh.js02.entity.Js03Log;
import com.wh.js02.mapper.Js03LogMapper;
import com.wh.js02.req.PageDTO;
import com.wh.js02.service.Js03LogService;
import com.wh.js02.vo.PageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class Js03LogServiceImpl implements Js03LogService{

    @Autowired
    private Js03LogMapper js03LogMapper;

    @Override
    public PageVo query(PageDTO pageDTO) {
        PageVo pageVo = new PageVo();

        int number = pageDTO.getPageSize();
        int offset =0;
        List<Js03Log> logs = js03LogMapper.query(offset,number);
        int totalLog = js03LogMapper.total();
        int totalPage = (int)Math.ceil((long)totalLog / (long)number);

        pageVo.setPageNo(pageDTO.getPageNo());
        pageVo.setTotal(totalLog);
        pageVo.setTotalPage(totalPage);
        pageVo.setPageSize(number);
        pageVo.setListBody(logs);

        return pageVo;
    }

    @Override
    public int deleteById(int id) {
        return js03LogMapper.deleteById(id);
    }

    @Override
    public PageVo fuzzyQuery(PageDTO pageDTO) {
        PageVo pageVo = new PageVo();

        int number = pageDTO.getPageSize();
        int offset = 0;
        String searchKey = pageDTO.getSearchKey();
        Date startTime = pageDTO.getStartTime();
        Date endTime = pageDTO.getEndTime();
        List<Js03Log> logs = js03LogMapper.fuzzyQuery(offset,number,searchKey,startTime,endTime);
        int total = js03LogMapper.totalFuzzy(searchKey,startTime,endTime);
        int totalPage = (int)Math.ceil((long)total / (long)number);


        pageVo.setListBody(logs);
        pageVo.setTotal(total);
        pageVo.setTotalPage(totalPage);
        pageVo.setPageSize(number);
        pageVo.setPageNo(1);
        return pageVo;
    }


}

