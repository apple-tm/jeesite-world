package com.example.js01.mapper;

import com.example.js01.entity.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface InsuranceOrderMapper {

    Integer addInsuranceOrder(InsuranceOrder insuranceOrder);

    Integer addInsuranceOrderToCar(List<InsuranceOrderToCar> insuranceOrderToCarList);

    Integer addInsuranceOrderToRisk(List<InsuranceOrderToRisk> insuranceOrderToRiskList);

    InsuranceOrder queryOrder(InsuranceOrder insuranceOrder);

    List<InsuranceOrder> rangQuery(QueryCommon queryCommon);

    /**
     * 订单Id查询订单（改）
     * @param insuranceOrder
     * @return
     */
    InsuranceOrder queryorder(InsuranceOrder insuranceOrder);

    /**
     * 通过被保险人名字查询订单
     * @param theInsured
     * @return
     */
    List<InsuranceOrder> queryOrderByTheInsuredName(TheInsured theInsured);

    /**
     * 通过保险公司名称查询订单
     * @param insurer
     * @return
     */
    List<InsuranceOrder> queryOrderByInsurerName(Insurer insurer);

    /**
     * 通过车牌号码查询订单
     * @param insuranceVehicles
     * @return
     */
    List<InsuranceOrder> queryOrderByplateNumber(InsuranceVehicles insuranceVehicles);
}
