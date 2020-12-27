package com.example.js01.entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

public class RegisterUser {
    @JSONField(ordinal = 1)
    private Integer id;
    @JSONField(ordinal = 2)
    private String name;
    // transient 关键字修改属性，可以让属性不进行序列化，如修饰密码等不应该泄露的信息
    private transient String password;
    // 校验password和rePassword是否一致 注意密码脱敏加密
    private String rePassword;
    private String email;
    @JSONField(ordinal = 3)
    private String phone;
    private String address;
    // 注册状态 1正常 2审核 3弃用
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
    private Integer startPage;
    private Integer pageSize;

    public RegisterUser() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRePassword() {
        return rePassword;
    }

    public void setRePassword(String rePassword) {
        this.rePassword = rePassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public Integer getStartPage() {
        return startPage;
    }

    public void setStartPage(Integer startPage) {
        this.startPage = startPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
