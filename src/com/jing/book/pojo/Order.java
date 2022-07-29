package com.jing.book.pojo;

import java.io.Serializable;

/**
 * @author 刘昱江
 * @className Order
 * @description TODO
 * @date 2022/4/1 10:56
 */
public class Order implements Serializable {

    private Integer orderId;        //订单ID主键自增
    private String orderSequence;   //订单编号
    private String createTime;      //创建订单时间
    private Long totalCount;     //订单总数
    private Double totalAmount;     //订单总价
    private Integer orderStatus;    //订单状态 - 待发货：0 已发货：1 确认收货：2
    private Integer userId;         //用户ID

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", orderSequence='" + orderSequence + '\'' +
                ", createTime='" + createTime + '\'' +
                ", totalCount='" + totalCount + '\'' +
                ", totalAmount='" + totalAmount + '\'' +
                ", orderStatus=" + orderStatus +
                ", userId=" + userId +
                '}';
    }

    public Order() {
    }

    public Order(Integer orderId, String orderSequence, String createTime, Long totalCount, Double totalAmount, Integer orderStatus, Integer userId) {
        this.orderId = orderId;
        this.orderSequence = orderSequence;
        this.createTime = createTime;
        this.totalCount = totalCount;
        this.totalAmount = totalAmount;
        this.orderStatus = orderStatus;
        this.userId = userId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getOrderSequence() {
        return orderSequence;
    }

    public void setOrderSequence(String orderSequence) {
        this.orderSequence = orderSequence;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

}
