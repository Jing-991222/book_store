package com.jing.book.dao.Impl;

import com.jing.book.dao.OrderDao;
import com.jing.book.pojo.Order;

import java.util.List;

public class OrderDaoImpl extends BaseDaoImpl implements OrderDao {
    @Override
    public Integer findOrderId(String sequence) {
        String sql = "select order_id from t_order where order_sequence = ?";
        return (Integer) getValue(sql,sequence);
    }

    @Override
    public void addOrder(Order order) {
        String sql = "insert into t_order values (null,?,?,?,?,?,?)";
        update(sql,
                order.getOrderSequence(),
                order.getCreateTime(),
                order.getTotalCount(),
                order.getTotalAmount(),
                order.getOrderStatus(),
                order.getUserId());
    }

    @Override
    public List<Order> getOrder(Integer id) {
        String sql = "select order_id orderId,order_sequence orderSequence,create_time createTime,total_count totalCount,total_amount totalAmount,order_status orderStatus,user_id userId from t_order where user_id = ?";
        return getList(Order.class,sql,id);
    }
}
