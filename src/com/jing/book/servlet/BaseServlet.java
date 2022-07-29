package com.jing.book.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

public class BaseServlet extends ViewBaseServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置请求编码
        req.setCharacterEncoding("utf-8");
        //设置响应的编码
        resp.setContentType("text/html;charset=utf-8");
        //获取所要请求的方法名
        String methodName = req.getParameter("method");
        //获取当前类.class对象
        Class<? extends BaseServlet> aClass = this.getClass();
        //通过反射获取对应的方法
        try {
            Method declaredMethod = aClass.getDeclaredMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
            //设置暴力反射
            declaredMethod.setAccessible(true);
            //执行方法   -->this指当前调用方法的对象
            declaredMethod.invoke(this,req,resp);
        } catch (Exception e) {
            System.out.println("反射方法有误,请检查:"+methodName);
            e.printStackTrace();
            throw  new RuntimeException(e);
        }
    }
}
