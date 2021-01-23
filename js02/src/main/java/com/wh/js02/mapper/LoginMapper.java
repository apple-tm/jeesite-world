package com.wh.js02.mapper;

import com.wh.js02.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LoginMapper {
    UserEntity selectOne(UserEntity userEntity);
}
