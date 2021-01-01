package com.example.js01.service.impl;

import com.example.js01.entity.TypesOfInsurance;
import com.example.js01.mapper.TypesOfInsuranceMapper;
import com.example.js01.service.TypesOfInsuranceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TypesOfInsuranceServiceImpl implements TypesOfInsuranceService {

    @Autowired
    private TypesOfInsuranceMapper insuranceMapper;

    @Override
    public String addInsurance(TypesOfInsurance insurance) {
        return insuranceMapper.addInsurance(insurance) == 1 ? "添加保险类型成功" : "添加保险类型失败";
    }


}
