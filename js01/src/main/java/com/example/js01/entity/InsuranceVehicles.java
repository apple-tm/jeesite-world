package com.example.js01.entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

/**
 * 保险车辆信息
 */
public class InsuranceVehicles {

    private Integer id;

    //号牌号码
    private String plateNumber;

    //号牌颜色
    private String numberPlateColour;

    //机动车种类
    private String vhicleType;

    //厂牌型号
    private String brandModel;

    //发动机号
    private String engineumberN;

    //VIN码/车架号
    private String vinOrFrameNumber;

    //车身颜色
    private String carColor;

    //核定载客
    private Integer nuclearLoad;

    //核定载质量
    private Double nuclearLoadMass;

    //排量
    private Double displacement;

    //协商实际价值
    private Double negotiatingRealValue;

    //初次登记日期
    private Date DateOfFirstRegistration;

    //使用性质
    private String natureOfUse;

    //车辆用途
    private String vehicleUse;

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
        return "InsuranceVehicles{" +
                "id=" + id +
                ", plateNumber='" + plateNumber + '\'' +
                ", numberPlateColour='" + numberPlateColour + '\'' +
                ", vhicleType='" + vhicleType + '\'' +
                ", brandModel='" + brandModel + '\'' +
                ", engineumberN='" + engineumberN + '\'' +
                ", vinOrFrameNumber='" + vinOrFrameNumber + '\'' +
                ", carColor='" + carColor + '\'' +
                ", nuclearLoad=" + nuclearLoad +
                ", nuclearLoadMass=" + nuclearLoadMass +
                ", displacement='" + displacement + '\'' +
                ", negotiatingRealValue=" + negotiatingRealValue +
                ", DateOfFirstRegistration=" + DateOfFirstRegistration +
                ", natureOfUse='" + natureOfUse + '\'' +
                ", vehicleUse='" + vehicleUse + '\'' +
                ", status='" + status + '\'' +
                ", createdTime=" + createdTime +
                ", createdBy='" + createdBy + '\'' +
                ", updatedTime=" + updatedTime +
                ", updatedBy='" + updatedBy + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public String getNumberPlateColour() {
        return numberPlateColour;
    }

    public void setNumberPlateColour(String numberPlateColour) {
        this.numberPlateColour = numberPlateColour;
    }

    public String getVhicleType() {
        return vhicleType;
    }

    public void setVhicleType(String vhicleType) {
        this.vhicleType = vhicleType;
    }

    public String getBrandModel() {
        return brandModel;
    }

    public void setBrandModel(String brandModel) {
        this.brandModel = brandModel;
    }

    public String getEngineumberN() {
        return engineumberN;
    }

    public void setEngineumberN(String engineumberN) {
        this.engineumberN = engineumberN;
    }

    public String getVinOrFrameNumber() {
        return vinOrFrameNumber;
    }

    public void setVinOrFrameNumber(String vinOrFrameNumber) {
        this.vinOrFrameNumber = vinOrFrameNumber;
    }

    public String getCarColor() {
        return carColor;
    }

    public void setCarColor(String carColor) {
        this.carColor = carColor;
    }

    public Integer getNuclearLoad() {
        return nuclearLoad;
    }

    public void setNuclearLoad(Integer nuclearLoad) {
        this.nuclearLoad = nuclearLoad;
    }

    public Double getNuclearLoadMass() {
        return nuclearLoadMass;
    }

    public void setNuclearLoadMass(Double nuclearLoadMass) {
        this.nuclearLoadMass = nuclearLoadMass;
    }

    public Double getDisplacement() {
        return displacement;
    }

    public void setDisplacement(Double displacement) {
        this.displacement = displacement;
    }

    public Double getNegotiatingRealValue() {
        return negotiatingRealValue;
    }

    public void setNegotiatingRealValue(Double negotiatingRealValue) {
        this.negotiatingRealValue = negotiatingRealValue;
    }

    public Date getDateOfFirstRegistration() {
        return DateOfFirstRegistration;
    }

    public void setDateOfFirstRegistration(Date dateOfFirstRegistration) {
        DateOfFirstRegistration = dateOfFirstRegistration;
    }

    public String getNatureOfUse() {
        return natureOfUse;
    }

    public void setNatureOfUse(String natureOfUse) {
        this.natureOfUse = natureOfUse;
    }

    public String getVehicleUse() {
        return vehicleUse;
    }

    public void setVehicleUse(String vehicleUse) {
        this.vehicleUse = vehicleUse;
    }
}
