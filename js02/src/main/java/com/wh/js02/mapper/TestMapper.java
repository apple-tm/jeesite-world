package com.wh.js02.mapper;

import com.wh.js02.entity.Test;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TestMapper {

    Integer testInsertOne(Test test);
}
