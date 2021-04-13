package com.example.js01.mapper;

import com.example.js01.entity.InsuranceOrderToCar;
import com.example.js01.entity.InsuranceVehicles;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface InsuranceVehiclesMapper {
    Integer addInsuranceVehicles(InsuranceVehicles insuranceVehicles);

    //通过订单id查询中间车表
    List<InsuranceVehicles> queryInsuranceVehicles(Integer id);
}
