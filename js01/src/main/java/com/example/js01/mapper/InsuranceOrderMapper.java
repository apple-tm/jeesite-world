package com.example.js01.mapper;

import com.example.js01.entity.InsuranceOrder;
import com.example.js01.entity.InsuranceOrderToCar;
import com.example.js01.entity.InsuranceOrderToRisk;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface InsuranceOrderMapper {

    Integer addInsuranceOrder(InsuranceOrder insuranceOrder);

    Integer addInsuranceOrderToCar(List<InsuranceOrderToCar> insuranceOrderToCarList);

    Integer addInsuranceOrderToRisk(List<InsuranceOrderToRisk> insuranceOrderToRiskList);

}
