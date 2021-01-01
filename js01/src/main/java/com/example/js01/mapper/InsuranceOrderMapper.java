package com.example.js01.mapper;

import com.example.js01.entity.InsuranceOrder;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface InsuranceOrderMapper {
    Integer addInsuranceOrder(InsuranceOrder insuranceOrder);
}
