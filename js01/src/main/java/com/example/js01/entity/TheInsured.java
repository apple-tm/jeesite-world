package com.example.js01.entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

/**
 * 被保险人
 */
public class TheInsured {

    private Integer id;

    //被保险人
    private String insuredName;

    //被保险人证件号码
    private String insuredIdNumber;

    //行驶证车主
    private String driverSLicense;

    //地址
    private String address;

    //联系电话
    private String telephoneNumber;

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
        return "TheInsured{" +
                "id=" + id +
                ", insuredName='" + insuredName + '\'' +
                ", insuredIdNumber='" + insuredIdNumber + '\'' +
                ", driverSLicense='" + driverSLicense + '\'' +
                ", address='" + address + '\'' +
                ", telephoneNumber='" + telephoneNumber + '\'' +
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

    public String getInsuredName() {
        return insuredName;
    }

    public void setInsuredName(String insuredName) {
        this.insuredName = insuredName;
    }

    public String getInsuredIdNumber() {
        return insuredIdNumber;
    }

    public void setInsuredIdNumber(String insuredIdNumber) {
        this.insuredIdNumber = insuredIdNumber;
    }

    public String getDriverSLicense() {
        return driverSLicense;
    }

    public void setDriverSLicense(String driverSLicense) {
        this.driverSLicense = driverSLicense;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
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
}
