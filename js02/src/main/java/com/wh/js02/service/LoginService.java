package com.wh.js02.service;

import com.wh.js02.entity.UserEntity;
import com.wh.js02.util.RedisUtil;

public interface LoginService {
    String login(UserEntity userEntity);
}
