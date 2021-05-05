package com.wh.js02.service;

import com.wh.js02.entity.Js03Log;
import com.wh.js02.req.PageDTO;
import com.wh.js02.vo.PageVo;

import java.util.List;

public interface Js03LogService {
    //查询日志
    PageVo query(PageDTO pageDTO);

    //删除日志
    int deleteById(int id);

    //模糊查询日志
    PageVo fuzzyQuery(PageDTO pageDTO);
}
