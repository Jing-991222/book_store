package com.jing.book.servlet;

import com.jing.book.pojo.*;
import com.jing.book.service.BookService;
import com.jing.book.service.CartService;
import com.jing.book.service.Impl.BookServiceImpl;
import com.jing.book.service.Impl.CartServiceImpl;
import com.jing.book.util.JDBCTools;
import com.jing.book.util.JsonUtils;
import com.jing.book.vo.ResultData;
import com.sun.org.apache.bcel.internal.generic.NEW;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@WebServlet("/cart")
public class CartServlet extends BaseServlet{

    private CartService cartService = new CartServiceImpl();
    private BookService bookService = new BookServiceImpl();


    /**
     *跳转至购物车
     */
    protected void toCart(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processTemplate("cart/cart",req,resp);
    }

    /**
     * 添加书籍到购物车
     */
    protected void addCart(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //获取添加图书的id
        int bookId = Integer.parseInt(req.getParameter ("bookId"));
        //根据图书id获取图书
        Book book = bookService.findBookById(bookId);
        //获取当前用户
        User user = (User) req.getSession().getAttribute("user");
        Integer userId = user.getId();
        //判断当前购物车中是否有该书籍
        Cart cartItem = cartService.findItem(userId,bookId);
        if (cartItem == null){
            //将书籍添加至购物车
            cartService.addBook(userId,bookId);
        }else {
            //更新书籍的数量
            cartService.updateCount(cartItem);
        }
        //获取购物车中的总购物书的数量
        long count = cartService.getTotalCount(userId);
        req.getSession().setAttribute("count",count);
        //将购物车中书数量返回
        String json = JsonUtils.json(ResultData.success(count));
        //将数据返回至前端
        resp.getWriter().write(json);

    }

    /**
     * 展现当前用户所有订单
     */
    protected void getItems(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取当前用户id
        User user = (User) req.getSession().getAttribute("user");
        Integer userId = user.getId();
        //根据用户id查找用户的所有订单信息
        List<Item> list = cartService.findItemsByUserId(userId);
        list.forEach(item -> {
            Integer count = item.getCount();
            Double price = item.getPrice();
            double amount = new BigDecimal(price + "").multiply(new BigDecimal(count + "")).doubleValue();
            item.setAmount(amount);
        });
        Result result = new Result();
        result.setList(list);
        //将List传回给前端
        long totalCount = cartService.getTotalCount(userId);
        req.getSession().setAttribute("count",totalCount);
        String json = JsonUtils.json(ResultData.success(result));
        resp.getWriter().write(json);
    }

    /**
     * 删除一项
     */
    protected void deleteItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int id = Integer.parseInt(req.getParameter("id"));

        int rows = cartService.removeItem(id);

        if (rows>0){
            String json = JsonUtils.json(ResultData.success());

            resp.getWriter().write(json);
        }

    }

    /**
     * 清空购物车

     */
    protected void clearCart(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        User user = (User) req.getSession().getAttribute("user");

        int len = cartService.clearCart(user.getId());

        req.getSession().setAttribute("count",0);

        if (len > 0){
            String json = JsonUtils.json(ResultData.success());
            resp.getWriter().write(json);
        }

    }

    /**
     * 更新购物项数量
     */
    protected void updateItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        int count = Integer.parseInt(req.getParameter("count"));
        int len = cartService.updateCount(id,count);
        if (len > 0){
            String json = JsonUtils.json(ResultData.success());
            resp.getWriter().write(json);
        }

    }

}
