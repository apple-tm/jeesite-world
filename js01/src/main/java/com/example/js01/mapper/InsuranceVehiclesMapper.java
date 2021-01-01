package com.example.js01.mapper;

import com.example.js01.entity.InsuranceVehicles;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface InsuranceVehiclesMapper {
    Integer addInsuranceVehicles(InsuranceVehicles insuranceVehicles);
}
