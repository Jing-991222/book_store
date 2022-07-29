package com.jing.book.servlet;

import com.jing.book.pojo.User;
import com.jing.book.service.CartService;
import com.jing.book.service.Impl.CartServiceImpl;
import com.jing.book.service.Impl.UserServiceImpl;
import com.jing.book.service.UserService;
import com.jing.book.util.JsonUtils;
import com.jing.book.vo.ResultData;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

@WebServlet("/user")
public class UserServlet extends BaseServlet{

    private UserService userService = new UserServiceImpl();
    private CartService cartService = new CartServiceImpl();

    /**+
     *跳转到登录界面
     */
    protected void toLogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processTemplate("user/login",req,resp);
    }

    /**+
     *跳转到注册界面
     */
    protected void toRegister(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processTemplate("user/regist",req,resp);
    }

    /**+
     * 注册业务
     */
    protected void register(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = new User();
        try {
            BeanUtils.populate(user,req.getParameterMap());
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        userService.addUser(user);
        resp.sendRedirect(req.getContextPath() + "/index.html");
    }

    /**
     * 验证用户名唯一性
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void checkUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        long line = userService.checkUsername(username);
        if (line > 0){
            resp.getWriter().write(JsonUtils.json(ResultData.fail()));
        }else{
            resp.getWriter().write(JsonUtils.json(ResultData.success()));
        }
    }


    /**
     * 校验验证码是否正确
     */
    protected void checkCode(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String code = req.getParameter("code");
        String realCode = (String) req.getSession().getAttribute("KAPTCHA_SESSION_KEY");
        if (realCode.equals(code)){
            resp.getWriter().write(JsonUtils.json(ResultData.success()));
        }else{
            resp.getWriter().write(JsonUtils.json(ResultData.fail()));
        }

    }

    /**
     * 登录业务
     */
    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = new User();
        try {
            BeanUtils.populate(user,req.getParameterMap());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        User userInfo = userService.login(user);

        if (userInfo == null){
            req.setAttribute("msg","用户名或者密码错误");
            processTemplate("user/login",req,resp);
        }else {
            req.getSession().setAttribute("user",userInfo);
            long totalCount = cartService.getTotalCount(userInfo.getId());
            req.getSession().setAttribute("count",totalCount);
            resp.sendRedirect(req.getContextPath() + "/index.html");
        }
    }

    /**
     * 注销
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().removeAttribute("user");
        resp.sendRedirect(req.getContextPath() + "/index.html");
    }

}
