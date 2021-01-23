package com.wh.js02.entity;

import java.util.Date;

public class Js02User extends BaseEntity{
    private Integer userId;

    private String userCode;

    private String userName;

    private String userPassword;

    private String reUserPassword;

    private String userPhone;

    private Integer userGender;

    private String userEmail;

    private Integer userDeptId;

    private Integer userRoleId;

    private Js02Dept js02Dept;

    private String deptName;

    private String roleName;

    private Js02Role js02Role;


    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getReUserPassword() {
        return reUserPassword;
    }

    public void setReUserPassword(String reUserPassword) {
        this.reUserPassword = reUserPassword;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public Integer getUserGender() {
        return userGender;
    }

    public void setUserGender(Integer userGender) {
        this.userGender = userGender;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public Integer getUserDeptId() {
        return userDeptId;
    }

    public void setUserDeptId(Integer userDeptId) {
        this.userDeptId = userDeptId;
    }

    public Integer getUserRoleId() {
        return userRoleId;
    }

    public void setUserRoleId(Integer userRoleId) {
        this.userRoleId = userRoleId;
    }

    public Js02Role getJs02Role() {
        return js02Role;
    }

    public void setJs02Role(Js02Role js02Role) {
        this.js02Role = js02Role;
    }

    public Js02Dept getJs02Dept() {
        return js02Dept;
    }

    public void setJs02Dept(Js02Dept js02Dept) {
        this.js02Dept = js02Dept;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }


}