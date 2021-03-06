package com.wh.js02.service;

import com.wh.js02.entity.Js03User;
import com.wh.js02.entity.ResultVo;
import com.wh.js02.req.PageDTO;
import com.wh.js02.vo.PageVo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface Js03UserService {

    ResultVo userLogin(Js03User user) throws Exception;

    ResultVo insertUser(Js03User user);

    ResultVo updateUserById(Js03User user);

    ResultVo selectUserById(Js03User user);
    ResultVo updateStatus(Js03User user);
    ResultVo<PageVo<List<Js03User>>> pageQuery(PageDTO pageDTO);
    ResultVo<PageVo<List<Js03User>>> fuzzyQuery(PageDTO pageDTO);

    ResultVo insertBatch(MultipartFile file);

}
