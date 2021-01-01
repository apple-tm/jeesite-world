package com.example.js01.service.impl;

import com.example.js01.entity.*;
import com.example.js01.mapper.InsuranceOrderMapper;
import com.example.js01.mapper.InsuranceVehiclesMapper;
import com.example.js01.mapper.TypesOfInsuranceMapper;
import com.example.js01.service.InsuranceOrderService;
import org.omg.CORBA.INTERNAL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class InsuranceOrderServiceImpl implements InsuranceOrderService {

    @Autowired
    private InsuranceOrderMapper insuranceOrderMapper;

    @Autowired
    private TypesOfInsuranceMapper typesOfInsuranceMapper;

    @Override
    // 测试事务作用 todo
    @Transactional
    public String addInsuranceOrder(InsuranceOrder insuranceOrder) {
        // 1.插入订单 返回订单ID
        //  1.1 查询保险信息，计算保费
        //  1.2 组装险种 ID 集合
        List<Integer> idList = new ArrayList<>(insuranceOrder.getTypesOfInsurance().size());
        List<TypesOfInsurance> typesOfInsuranceList = insuranceOrder.getTypesOfInsurance();
        for (TypesOfInsurance item3 : typesOfInsuranceList) {
            idList.add(item3.getId());
        }
        List<TypesOfInsurance> typesOfInsuranceList1 = typesOfInsuranceMapper.selectByIn(idList);
        // 1.3计算保费
        BigDecimal feeSum = new BigDecimal("0");
        for (TypesOfInsurance item4 : typesOfInsuranceList1) {
            BigDecimal curFee = new BigDecimal(item4.getInsurancePremium()).setScale(2, BigDecimal.ROUND_HALF_UP);
            feeSum = feeSum.add(curFee);
        }
        insuranceOrder.setFeeSum(feeSum);
        Integer insureCount2 = insuranceOrderMapper.addInsuranceOrder(insuranceOrder);
        if (!insureCount2.equals(1)) {
            return "插入order信息错误";
        }
        // 2.插入车辆信息
        List<InsuranceOrderToCar> insuranceOrderToCarList = new ArrayList<>(insuranceOrder.getInsuranceVehicles().size());
        List<InsuranceVehicles> insuranceVehiclesList = insuranceOrder.getInsuranceVehicles();
        for (InsuranceVehicles item1 : insuranceVehiclesList) {
            InsuranceOrderToCar insuranceOrderToCar = new InsuranceOrderToCar();
            insuranceOrderToCar.setCarId(item1.getId());
            // 订单 ID 返回之后插入
            insuranceOrderToCar.setOrderId(insuranceOrder.getId());
            insuranceOrderToCarList.add(insuranceOrderToCar);
        }
        Integer carCount = insuranceOrderMapper.addInsuranceOrderToCar(insuranceOrderToCarList);
        if (!carCount.equals(insuranceOrder.getInsuranceVehicles().size())) {
            return "插入车辆信息错误";
        }
        // 3.插入保险列表
        List<InsuranceOrderToRisk> insuranceOrderToRiskList = new ArrayList<>(insuranceOrder.getTypesOfInsurance().size());
        for (TypesOfInsurance item2 : typesOfInsuranceList) {
            InsuranceOrderToRisk insuranceOrderToRisk = new InsuranceOrderToRisk();
            insuranceOrderToRisk.setInsureId(item2.getId());
            // 订单 ID 返回之后插入
            insuranceOrderToRisk.setOrderId(insuranceOrder.getId());
            insuranceOrderToRiskList.add(insuranceOrderToRisk);
        }
        Integer insureCount = insuranceOrderMapper.addInsuranceOrderToRisk(insuranceOrderToRiskList);
        if (!carCount.equals(insuranceOrder.getInsuranceVehicles().size())) {
            return "插入白保险信息错误";
        }

        return "order success";
    }
}
