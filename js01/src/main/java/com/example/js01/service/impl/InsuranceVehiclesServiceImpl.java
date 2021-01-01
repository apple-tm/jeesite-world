package com.example.js01.service.impl;

import com.example.js01.entity.InsuranceVehicles;
import com.example.js01.mapper.InsuranceVehiclesMapper;
import com.example.js01.service.InsuranceVehiclesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InsuranceVehiclesServiceImpl implements InsuranceVehiclesService {
    @Autowired
    private InsuranceVehiclesMapper insuranceVehiclesMapper;
    @Override
    public String addInsuranceVehicles(InsuranceVehicles insuranceVehicles) {
        return insuranceVehiclesMapper.addInsuranceVehicles(insuranceVehicles) == 1 ? "添加车辆信息成功" : "fail";

    }
}
