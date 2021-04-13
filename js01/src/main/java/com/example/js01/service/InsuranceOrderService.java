package com.example.js01.service;

import com.example.js01.entity.*;

import java.util.List;

public interface InsuranceOrderService {
    String addInsuranceOrder(InsuranceOrder insuranceOrder);

    InsuranceOrder queryOrder(InsuranceOrder insuranceOrder);

    List<InsuranceOrder> rangQuery(QueryCommon queryCommon);

    InsuranceOrder queryorder(InsuranceOrder insuranceOrder);

    List<InsuranceOrder> queryOrderByTheInsuredName(TheInsured theInsured);

    List<InsuranceOrder> queryOrderByInsurerName(Insurer insurer);

    List<InsuranceOrder> queryOrderByplateNumber(InsuranceVehicles insuranceVehicles);
}
