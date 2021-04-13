package com.wh.js02.entity;

import java.util.Date;

public class Js02Log {
    private Integer js02LogId;

    private String js02LogUrl;

    private String js02LogMethod;

    private Date js02LogTime;

    private Integer js02UserId;

    public Integer getJs02LogId() {
        return js02LogId;
    }

    public void setJs02LogId(Integer js02LogId) {
        this.js02LogId = js02LogId;
    }

    public String getJs02LogUrl() {
        return js02LogUrl;
    }

    public void setJs02LogUrl(String js02LogUrl) {
        this.js02LogUrl = js02LogUrl == null ? null : js02LogUrl.trim();
    }

    public String getJs02LogMethod() {
        return js02LogMethod;
    }

    public void setJs02LogMethod(String js02LogMethod) {
        this.js02LogMethod = js02LogMethod == null ? null : js02LogMethod.trim();
    }

    public Date getJs02LogTime() {
        return js02LogTime;
    }

    public void setJs02LogTime(Date js02LogTime) {
        this.js02LogTime = js02LogTime;
    }

    public Integer getJs02UserId() {
        return js02UserId;
    }

    public void setJs02UserId(Integer js02UserId) {
        this.js02UserId = js02UserId;
    }
}