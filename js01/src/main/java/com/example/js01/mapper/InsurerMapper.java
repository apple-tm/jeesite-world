package com.example.js01.mapper;

import com.example.js01.entity.Insurer;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface InsurerMapper {
    Integer addInsurer(Insurer insurer);
    Insurer queryInsurer(Integer insurerId);
}
