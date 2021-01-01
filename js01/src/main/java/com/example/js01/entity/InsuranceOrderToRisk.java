package com.example.js01.entity;

/**
 *订单
 */
public class InsuranceOrderToRisk extends BaseEntity {

    private Integer orderId;
    private Integer insureId;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getInsureId() {
        return insureId;
    }

    public void setInsureId(Integer insureId) {
        this.insureId = insureId;
    }
}
