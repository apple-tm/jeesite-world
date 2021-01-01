package com.example.js01.entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

/**
 * 被保险人
 */
public class TheInsured extends BaseEntity {

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

}
