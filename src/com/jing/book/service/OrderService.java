package com.jing.book.service;

import com.jing.book.pojo.Order;
import com.jing.book.pojo.Result;

import java.util.List;

public interface OrderService {
    String addOrder(Integer userId,Result result);

    List<Order> getOrder(Integer id);
}
