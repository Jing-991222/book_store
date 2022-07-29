package com.jing.book.servlet;

import com.jing.book.util.JDBCTools;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebFilter("/*")
public class StoreFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)  {
        Connection connection = null;
        try {
            connection = JDBCTools.getConnection();
            connection.setAutoCommit(false);
            filterChain.doFilter(servletRequest,servletResponse);
            connection.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            JDBCTools.releaseConnection();
        }


    }
}
