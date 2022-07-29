package com.jing.book.pojo;

import java.math.BigDecimal;
import java.util.List;


public class Result {

    private List<Item> list;

    private Double totalAmount;

    private int count;


    public List<Item> getList() {
        return list;
    }

    public void setList(List<Item> list) {
        this.list = list;
        BigDecimal price = new BigDecimal("0");
        for (Item item : list) {
            count += item.getCount();
            price = price.add(new BigDecimal(item.getAmount() + ""));
        }
        totalAmount = price.doubleValue();
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public int getCount() {
        return count;
    }

}
