package com.example.js01.service;

import com.example.js01.entity.LoginReqDTO;
import com.example.js01.entity.RegisterUser;

import java.util.List;

public interface RegisterUserService {

    Integer registerUser(RegisterUser registerUser);

    Integer registerManyUser(List<RegisterUser> registerUserList);

    Integer deleteOneUser(RegisterUser registerUser);

    Integer deleteManyUser(List<RegisterUser> registerUserList);

    Integer updateOneUser(RegisterUser registerUser);

    /**
     * 使用 phone 查询 唯一用户
     * @param phone
     * @return
     */
    List<RegisterUser> queryOneUser(String phone) throws Exception;

    List<RegisterUser> login(LoginReqDTO loginReqDTO) throws Exception;

    List<RegisterUser> pageQuery(RegisterUser registerUser) throws Exception;
}
