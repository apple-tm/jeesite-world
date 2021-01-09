package com.example.js01.entity;

public class QueryCommon {
    private Integer queryCommonId;
    private Insurer insurer ;
    private InsuranceVehicles insuranceVehicles ;
    private TheInsured theInsured ;
    private TypesOfInsurance typesOfInsurance ;
    private Integer pageNo;
    private Integer pageSize;
    private Double maxFeeSum;
    private Double minFeeSum;


    public Integer getQueryCommonId() {
        return queryCommonId;
    }

    public void setQueryCommonId(Integer queryCommonId) {
        this.queryCommonId = queryCommonId;
    }

    public Insurer getInsurer() {
        return insurer;
    }

    public void setInsurer(Insurer insurer) {
        this.insurer = insurer;
    }

    public InsuranceVehicles getInsuranceVehicles() {
        return insuranceVehicles;
    }

    public void setInsuranceVehicles(InsuranceVehicles insuranceVehicles) {
        this.insuranceVehicles = insuranceVehicles;
    }

    public TheInsured getTheInsured() {
        return theInsured;
    }

    public void setTheInsured(TheInsured theInsured) {
        this.theInsured = theInsured;
    }

    public TypesOfInsurance getTypesOfInsurance() {
        return typesOfInsurance;
    }

    public void setTypesOfInsurance(TypesOfInsurance typesOfInsurance) {
        this.typesOfInsurance = typesOfInsurance;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Double getMaxFeeSum() {
        return maxFeeSum;
    }

    public void setMaxFeeSum(Double maxFeeSum) {
        this.maxFeeSum = maxFeeSum;
    }

    public Double getMinFeeSum() {
        return minFeeSum;
    }

    public void setMinFeeSum(Double minFeeSum) {
        this.minFeeSum = minFeeSum;
    }
}
