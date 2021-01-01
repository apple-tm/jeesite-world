package com.example.js01.entity;

//修改密码类
public class ChangePassword {
    private String phone;
    private String password;
    private String newPassword;
    private String newRePassword;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getNewRePassword() {
        return newRePassword;
    }

    public void setNewRePassword(String newRePassword) {
        this.newRePassword = newRePassword;
    }
}
