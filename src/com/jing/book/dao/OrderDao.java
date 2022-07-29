package com.jing.book.dao;

import com.jing.book.pojo.Order;

import java.util.List;

public interface OrderDao {
    Integer findOrderId(String sequence);

    void addOrder(Order order);

    List<Order> getOrder(Integer id);
}
