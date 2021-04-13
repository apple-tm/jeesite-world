package com.example.js01.entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 *订单
 */
public class InsuranceOrder extends BaseEntity {

    private static final  long  serialVersionUID = 1823712083701287L;
    private Integer id;
    private BigDecimal feeSum;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date insureStartTime;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date insureEndTime;
    private TheInsured theInsured;
    private Insurer insurer;
    private List<TypesOfInsurance> typesOfInsurance;
    private List<InsuranceVehicles> insuranceVehicles ;

    private Integer insuredId;
    private Integer theInsuredId;

    public InsuranceOrder() {
    }

    public Integer getInsuredId() {
        return insuredId;
    }

    public void setInsuredId(Integer insuredId) {
        this.insuredId = insuredId;
    }

    public Integer getTheInsuredId() {
        return theInsuredId;
    }

    public void setTheInsuredId(Integer theInsuredId) {
        this.theInsuredId = theInsuredId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getFeeSum() {
        return feeSum;
    }

    public void setFeeSum(BigDecimal feeSum) {
        this.feeSum = feeSum;
    }

    public Date getInsureStartTime() {
        return insureStartTime;
    }

    public void setInsureStartTime(Date insureStartTime) {
        this.insureStartTime = insureStartTime;
    }

    public Date getInsureEndTime() {
        return insureEndTime;
    }

    public void setInsureEndTime(Date insureEndTime) {
        this.insureEndTime = insureEndTime;
    }

    public TheInsured getTheInsured() {
        return theInsured;
    }

    public void setTheInsured(TheInsured theInsured) {
        this.theInsured = theInsured;
    }

    public Insurer getInsurer() {
        return insurer;
    }

    public void setInsurer(Insurer insurer) {
        this.insurer = insurer;
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
}
