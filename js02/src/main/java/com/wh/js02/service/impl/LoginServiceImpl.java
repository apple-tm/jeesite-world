package com.wh.js02.service.impl;

import com.wh.js02.entity.UserEntity;
import com.wh.js02.mapper.LoginMapper;
import com.wh.js02.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private LoginMapper loginMapper;

    @Override
    public String login(UserEntity userEntity) {

        UserEntity user = loginMapper.selectOne(userEntity);
        if (!user.equals(null) && user.getUserPassword().equals(userEntity.getUserPassword())){
            return "登录成功";
        }else {
            return "fail";
        }


    }
}
