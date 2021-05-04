package com.wh.js02.service;

import com.wh.js02.entity.Js03UserFile;
import com.wh.js02.req.PageDTO;
import com.wh.js02.vo.PageVo;

import java.util.List;

public interface Js03UserFileService {

    int uploadFile(Js03UserFile js03UserFile) throws Exception;

    PageVo<List<Js03UserFile>> pageQuery(PageDTO pageDTO);

    int updatePicture(Js03UserFile js03UserFile);

    int deletePircture(Integer id);

    PageVo fuzzyQuery(PageDTO pageDTO);

}
