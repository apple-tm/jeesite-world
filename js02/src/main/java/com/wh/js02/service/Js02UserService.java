package com.wh.js02.service;

import com.wh.js02.entity.Js02User;
import com.wh.js02.req.PageDTO;
import com.wh.js02.vo.PageVo;

import java.util.List;

public interface Js02UserService {

    Js02User login(Js02User js02User);



    PageVo<List<Js02User>> getUserList(PageDTO pageDTO);

    Integer addUser(Js02User js02User);

    PageVo<List<Js02User>> fuzzyQuery(PageDTO pageDTO);

    Integer updateUser(Js02User js02User);

    Integer updateUserStatus(Integer userId);
}
