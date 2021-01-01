package com.example.js01.entity;

import com.alibaba.fastjson.annotation.JSONField;
import org.omg.CORBA.INTERNAL;

import java.util.Date;

/**
 * 保险类型类
 */
public class TypesOfInsurance  extends  BaseEntity{

    private Integer id;

    //承担险种（保险名）
    private String typeOfInsurance;

    //保险金额/责任限额
    private Double insuranceAmount;

    //每次事故绝对免赔额/率
    private String accidentAbsoluteDeductible;

    //不计免赔率 noCal...
    private String irrespectiveOfPercentage;

    //保险费
    private Double insurancePremium;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTypeOfInsurance() {
        return typeOfInsurance;
    }

    public void setTypeOfInsurance(String typeOfInsurance) {
        this.typeOfInsurance = typeOfInsurance;
    }

    public Double getInsuranceAmount() {
        return insuranceAmount;
    }

    public void setInsuranceAmount(Double insuranceAmount) {
        this.insuranceAmount = insuranceAmount;
    }

    public String getAccidentAbsoluteDeductible() {
        return accidentAbsoluteDeductible;
    }

    public void setAccidentAbsoluteDeductible(String accidentAbsoluteDeductible) {
        this.accidentAbsoluteDeductible = accidentAbsoluteDeductible;
    }

    public String getIrrespectiveOfPercentage() {
        return irrespectiveOfPercentage;
    }

    public void setIrrespectiveOfPercentage(String irrespectiveOfPercentage) {
        this.irrespectiveOfPercentage = irrespectiveOfPercentage;
    }

    public Double getInsurancePremium() {
        return insurancePremium;
    }

    public void setInsurancePremium(Double insurancePremium) {
        this.insurancePremium = insurancePremium;
    }
}
