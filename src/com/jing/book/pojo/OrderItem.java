package com.jing.book.pojo;

import java.io.Serializable;

/**
 * @author 刘昱江
 * @className OrderItem
 * @description TODO
 * @date 2022/4/1 10:58
 */
public class OrderItem implements Serializable{

    private Integer itemId;     //订单编号
    private String bookName;    //图书名称
    private Double price;       //图书价格
    private String imgPath;     //图片地址
    private Long itemCount;  //购买数量
    private Double itemAmount;  //购买金额
    private Integer orderId;    //订单编号 与Order表保持一致.

    public OrderItem(Integer itemId, String bookName, Double price, String imgPath, Long itemCount, Double itemAmount, Integer orderId) {
        this.itemId = itemId;
        this.bookName = bookName;
        this.price = price;
        this.imgPath = imgPath;
        this.itemCount = itemCount;
        this.itemAmount = itemAmount;
        this.orderId = orderId;
    }

    public OrderItem(){}

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public Long getItemCount() {
        return itemCount;
    }

    public void setItemCount(Long itemCount) {
        this.itemCount = itemCount;
    }

    public Double getItemAmount() {
        return itemAmount;
    }

    public void setItemAmount(Double itemAmount) {
        this.itemAmount = itemAmount;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }
}
