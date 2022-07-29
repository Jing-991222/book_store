package com.jing.book.service.Impl;

import com.jing.book.dao.Impl.OrderDaoImpl;
import com.jing.book.dao.Impl.OrderItemDaoImpl;
import com.jing.book.dao.OrderDao;
import com.jing.book.dao.OrderItemDao;
import com.jing.book.pojo.Order;
import com.jing.book.pojo.OrderItem;
import com.jing.book.pojo.Result;
import com.jing.book.service.OrderService;
import org.omg.CORBA.ORB;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

public class OrderServiceImpl implements OrderService {

    private OrderDao orderDao = new OrderDaoImpl();
    private OrderItemDao orderItemDao = new OrderItemDaoImpl();

    @Override
    public String addOrder(Integer userId,Result result) {

        Order order = new Order();

        order.setOrderId(null);

        String sequence = System.currentTimeMillis() + "";

        order.setOrderSequence(sequence);

        order.setCreateTime(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));

        order.setTotalCount((long) result.getCount());

        order.setTotalAmount(result.getTotalAmount());

        order.setOrderStatus(0);

        order.setUserId(userId);

        orderDao.addOrder(order);

        Integer orderId = orderDao.findOrderId(sequence);

        result.getList().forEach(item -> {
            OrderItem orderItem = new OrderItem();
            orderItem.setItemId(null);
            orderItem.setBookName(item.getTitle());
            orderItem.setPrice(item.getPrice());
            orderItem.setImgPath(item.getImgPath());
            orderItem.setItemCount(Long.valueOf(item.getCount()));
            orderItem.setItemAmount(item.getAmount());
            orderItem.setOrderId(orderId);
            orderItemDao.addItem(orderItem);
        });


        return sequence;
    }

    @Override
    public List<Order> getOrder(Integer id) {
        return orderDao.getOrder(id);
    }
}
