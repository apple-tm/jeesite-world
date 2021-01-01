package com.example.js01.mapper;

import com.example.js01.entity.TypesOfInsurance;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TypesOfInsuranceMapper {
    Integer addInsurance(TypesOfInsurance insurance);

    Integer deleteInsurance(List<String> insurances);
}
