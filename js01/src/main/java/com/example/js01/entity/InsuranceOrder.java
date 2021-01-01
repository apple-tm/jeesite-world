package com.example.js01.entity;

import java.util.List;

/**
 *订单
 */
public class InsuranceOrder {
    private Integer orderId;
    private TheInsured theInsured;
    private List<TypesOfInsurance> typesOfInsurance;
    private List<InsuranceVehicles> insuranceVehicles ;
    private Insurer insurer;


    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public TheInsured getTheInsured() {
        return theInsured;
    }

    public void setTheInsured(TheInsured theInsured) {
        this.theInsured = theInsured;
    }

    public List<TypesOfInsurance> getTypesOfInsurance() {
        return typesOfInsurance;
    }

    public void setTypesOfInsurance(List<TypesOfInsurance> typesOfInsurance) {
        this.typesOfInsurance = typesOfInsurance;
    }

    public List<InsuranceVehicles> getInsuranceVehicles() {
        return insuranceVehicles;
    }

    public void setInsuranceVehicles(List<InsuranceVehicles> insuranceVehicles) {
        this.insuranceVehicles = insuranceVehicles;
    }

    public Insurer getInsurer() {
        return insurer;
    }

    public void setInsurer(Insurer insurer) {
        this.insurer = insurer;
    }
}
