package com.example.js01.entity;

import com.alibaba.fastjson.annotation.JSONField;
import org.omg.CORBA.INTERNAL;

import java.util.Date;

/**
 * 保险类型类
 */
public class TypesOfInsurance {

    private Integer id;

    //承担险种（保险名）
    private String typeOfInsurance;

    //保险金额/责任限额
    private Double insuranceAmount;

    //每次事故绝对免赔额/率
    private String accidentAbsoluteDeductible;

    //不计免赔率
    private String irrespectiveOfPercentage;

    //保险费
    private Double insurancePremium;

    private String status;
    // 解析表单时间
//     @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    // 解析 JSON 时间
//     @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date createdTime;
    private String createdBy;
    //     @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//     @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date  updatedTime;
    private String updatedBy;


    @Override
    public String toString() {
        return "TypesOfInsurance{" +
                "id=" + id +
                ", typeOfInsurance='" + typeOfInsurance + '\'' +
                ", insuranceAmount=" + insuranceAmount +
                ", accidentAbsoluteDeductible='" + accidentAbsoluteDeductible + '\'' +
                ", irrespectiveOfPercentage='" + irrespectiveOfPercentage + '\'' +
                ", insurancePremium=" + insurancePremium +
                ", status='" + status + '\'' +
                ", createdTime=" + createdTime +
                ", createdBy='" + createdBy + '\'' +
                ", updatedTime=" + updatedTime +
                ", updatedBy='" + updatedBy + '\'' +
                '}';
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

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
