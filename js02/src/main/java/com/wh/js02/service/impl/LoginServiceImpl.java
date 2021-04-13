package com.wh.js02.service.impl;

import com.wh.js02.entity.UserEntity;
import com.wh.js02.mapper.LoginMapper;
import com.wh.js02.service.LoginService;
import com.wh.js02.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class LoginServiceImpl implements LoginService, HandlerInterceptor {

    @Autowired
    private LoginMapper loginMapper;
    @Autowired
    private RedisUtil redisUtil;

    @Override
    public String login(UserEntity userEntity) {

        UserEntity user = loginMapper.selectOne(userEntity);
        if (!user.equals(null) && user.getUserPassword().equals(userEntity.getUserPassword())){
            String uuid = UUID.randomUUID().toString().replaceAll("-","");
            redisUtil.setObject(uuid,"",30L, TimeUnit.MINUTES);
            return uuid;
        }else {
            return "fail";
        }

    }


}
