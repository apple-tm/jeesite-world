package com.wh.js02.service.impl;

import com.wh.js02.entity.UserEntity;
import com.wh.js02.mapper.RegisterMapper;
import com.wh.js02.service.RegisterService;
import com.wh.js02.util.Encryption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterServiceImpl implements RegisterService {

    @Autowired
    private RegisterMapper registerMapper;

    @Override
    public Integer register(UserEntity userEntity) {

        userEntity.setUserPassword(Encryption.encryp(userEntity.getUserPassword()));
        return registerMapper.insertUserOne(userEntity);
    }
}
