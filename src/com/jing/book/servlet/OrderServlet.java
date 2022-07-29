package com.jing.book.servlet;

import com.jing.book.pojo.*;
import com.jing.book.service.CartService;
import com.jing.book.service.Impl.CartServiceImpl;
import com.jing.book.service.Impl.OrderServiceImpl;
import com.jing.book.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@WebServlet("/order")
public class OrderServlet extends BaseServlet {

    private OrderService orderService = new OrderServiceImpl();
    private CartService cartService = new CartServiceImpl();

    /**
     * 结算订单
     */
    protected void addOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        User user = (User) req.getSession().getAttribute("user");
        Integer userId = user.getId();
        List<Item> itemList = cartService.findItemsByUserId(userId);
        itemList.forEach(item -> {
            Integer count = item.getCount();
            Double price = item.getPrice();
            double amount = new BigDecimal(price + "").multiply(new BigDecimal(count + "")).doubleValue();
            item.setAmount(amount);
        });
        Result result = new Result();
        result.setList(itemList);
        String sequence = orderService.addOrder(userId,result);
        cartService.clearCart(userId);
        req.getSession().setAttribute("count",0);
        req.setAttribute("sequence",sequence);
        processTemplate("cart/checkout",req,resp);
    }


    /**
     *  前往订单页面
     */
    protected void toOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        List<Order> list = orderService.getOrder(user.getId());
        req.getSession().setAttribute("orderList",list);
        processTemplate("order/order",req,resp);
    }
}
