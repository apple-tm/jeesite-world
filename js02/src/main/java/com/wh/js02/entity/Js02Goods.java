package com.wh.js02.entity;

import java.util.Date;

public class Js02Goods {
    private Integer js02GoodsId;

    private String js02GoodsName;

    private Long js02GoodsPrice;

    private String js02GoodsType;

    private Integer js02GoodsSum;

    private Date createTime;

    private String createBy;

    private Integer status;

    private Date updateTime;

    private Integer updateBy;

    public Integer getJs02GoodsId() {
        return js02GoodsId;
    }

    public void setJs02GoodsId(Integer js02GoodsId) {
        this.js02GoodsId = js02GoodsId;
    }

    public String getJs02GoodsName() {
        return js02GoodsName;
    }

    public void setJs02GoodsName(String js02GoodsName) {
        this.js02GoodsName = js02GoodsName == null ? null : js02GoodsName.trim();
    }

    public Long getJs02GoodsPrice() {
        return js02GoodsPrice;
    }

    public void setJs02GoodsPrice(Long js02GoodsPrice) {
        this.js02GoodsPrice = js02GoodsPrice;
    }

    public String getJs02GoodsType() {
        return js02GoodsType;
    }

    public void setJs02GoodsType(String js02GoodsType) {
        this.js02GoodsType = js02GoodsType == null ? null : js02GoodsType.trim();
    }

    public Integer getJs02GoodsSum() {
        return js02GoodsSum;
    }

    public void setJs02GoodsSum(Integer js02GoodsSum) {
        this.js02GoodsSum = js02GoodsSum;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Integer updateBy) {
        this.updateBy = updateBy;
    }
}