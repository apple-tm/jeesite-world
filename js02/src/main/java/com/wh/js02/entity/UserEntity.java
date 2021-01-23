package com.wh.js02.entity;

import org.omg.CORBA.INTERNAL;

public class UserEntity extends BaseEntity{

    private Integer userEntityId;
    private String phoneNumber;
    private String userPassword;
    private String userRePassword;
    // 角色对象 todo

    public Integer getUserEntityId() {
        return userEntityId;
    }

    public void setUserEntityId(Integer userEntityId) {
        this.userEntityId = userEntityId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserRePassword() {
        return userRePassword;
    }

    public void setUserRePassword(String userRePassword) {
        this.userRePassword = userRePassword;
    }
}
