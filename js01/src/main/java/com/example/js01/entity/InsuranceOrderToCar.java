package com.example.js01.entity;

/**
 *订单
 */
public class InsuranceOrderToCar extends BaseEntity {

    private Integer orderId;
    private Integer carId;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }
}
