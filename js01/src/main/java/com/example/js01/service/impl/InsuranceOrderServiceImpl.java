package com.example.js01.service.impl;

import com.example.js01.entity.InsuranceOrder;
import com.example.js01.entity.InsuranceVehicles;
import com.example.js01.entity.TheInsured;
import com.example.js01.mapper.InsuranceOrderMapper;
import com.example.js01.mapper.InsuranceVehiclesMapper;
import com.example.js01.service.InsuranceOrderService;
import org.omg.CORBA.INTERNAL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InsuranceOrderServiceImpl implements InsuranceOrderService {

    @Autowired
    private InsuranceOrderMapper insuranceOrderMapper;

    @Autowired
    private InsuranceVehiclesMapper insuranceVehiclesMapper;

    @Override
    public String addInsuranceOrder(InsuranceOrder insuranceOrder) {
        List<Integer> carIdList = new ArrayList<>();
        Integer theInsuredId;

        List<InsuranceVehicles> insuranceVehiclesList = insuranceOrder.getInsuranceVehicles();
        for (InsuranceVehicles insuranceVehicles : insuranceVehiclesList) {
            if (insuranceVehicles.getId() == null) {
                // 插入不存在的汽车 返回id集合 todo
                insuranceVehiclesMapper.addInsuranceVehicles(insuranceVehicles);
                carIdList.add(insuranceVehicles.getId());
            } else {
                carIdList.add(insuranceVehicles.getId());
            }
        }

        StringBuilder carFiled = new StringBuilder();
        for (Integer carItem : carIdList) {
            carFiled.append(carFiled).append(",");
        }


        TheInsured theInsured = insuranceOrder.getTheInsured();
        if (theInsured.getId() == null) {

        } else {
            theInsuredId = theInsured.getId();
        }
        insuranceOrder.getTypesOfInsurance();


        return null;
    }
}
