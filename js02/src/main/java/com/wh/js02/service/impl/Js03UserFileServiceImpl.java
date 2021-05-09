package com.wh.js02.service.impl;

import com.wh.js02.entity.Js03UserFile;
import com.wh.js02.mapper.Js03UserFileMapper;
import com.wh.js02.req.PageDTO;
import com.wh.js02.service.Js03UserFileService;
import com.wh.js02.vo.PageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class Js03UserFileServiceImpl implements Js03UserFileService{

    @Autowired
    private Js03UserFileMapper js03UserFileMapper;

    @Override
    public int uploadFile(Js03UserFile js03UserFile) throws Exception {
        return js03UserFileMapper.insertSelective(js03UserFile);
    }

    @Override
    public PageVo<List<Js03UserFile>> pageQuery(PageDTO pageDTO) {
        //分页    limit offset num
        int offset = (pageDTO.getPageNo() - 1) * pageDTO.getPageSize();
        int number = pageDTO.getPageSize();

        //当前页图片
        List<Js03UserFile> userFiles = js03UserFileMapper.pageQuery(offset,number,pageDTO.getUserId());
        //状态为1的正常图片（总记录数）
        int total = js03UserFileMapper.queryUserFileNum();
        //总页数
        int totalPageNum = total % number == 0 ? total / number : total / number + 1;
        PageVo<List<Js03UserFile>> pageVo = new PageVo<>();
        pageVo.setPageNo(pageDTO.getPageNo());
        pageVo.setPageSize(pageDTO.getPageSize());
        pageVo.setTotal(total);
        pageVo.setTotalPage(totalPageNum);
        pageVo.setListBody(userFiles);

        return pageVo;
    }

    @Override
    public int updatePicture(Js03UserFile js03UserFile) {
        int count = js03UserFileMapper.updatePicture(js03UserFile);
        return count;
    }

    @Override
    public int deletePircture(Integer id) {
        return js03UserFileMapper.deletePicture(id);
    }

    @Override
    public PageVo fuzzyQuery(PageDTO pageDTO) {
        //limit offset pageSize
        int offset = (pageDTO.getPageNo() - 1) * pageDTO.getPageSize();
        int number = pageDTO.getPageSize();
        Date startTime = pageDTO.getStartTime();
        Date endTime = pageDTO.getEndTime();
        //当前页图片
        List<Js03UserFile> js03UserFiles = js03UserFileMapper.fuzzyQuery(offset,number,pageDTO.getUserId(),pageDTO.getSearchKey(),startTime,endTime);
        //总记录数
        int totalPic = js03UserFileMapper.fuzzyQueryFileNum(pageDTO.getSearchKey());
        //总页数 = 总记录数 / pageSize，向上取整
        int totalPage = totalPic % number == 0 ? totalPic / number : totalPic / number + 1;

        PageVo<List<Js03UserFile>> pageVo = new PageVo();
        pageVo.setListBody(js03UserFiles);
        pageVo.setPageSize(number);
        pageVo.setTotalPage(totalPage);
        pageVo.setTotal(totalPic);
        pageVo.setPageNo(pageDTO.getPageNo());

        return pageVo;
    }
}
