package com.jing.book.dao.Impl;

import com.jing.book.dao.OrderItemDao;
import com.jing.book.pojo.OrderItem;

public class OrderItemDaoImpl extends BaseDaoImpl implements OrderItemDao {
    @Override
    public void addItem(OrderItem orderItem) {
        String sql = "insert into t_order_item values (null,?,?,?,?,?,?)";
        update(sql,
                orderItem.getBookName(),
                orderItem.getPrice(),
                orderItem.getImgPath(),
                orderItem.getItemCount(),
                orderItem.getItemAmount(),
                orderItem.getOrderId());
    }
}
