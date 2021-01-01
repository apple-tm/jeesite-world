package com.example.js01.mapper;

import com.example.js01.entity.TheInsured;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TheInsuredMapper {
    Integer addTheInsured(TheInsured theInsured);
}
