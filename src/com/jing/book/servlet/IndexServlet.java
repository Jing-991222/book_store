package com.jing.book.servlet;

import com.jing.book.pojo.Book;
import com.jing.book.service.BookService;
import com.jing.book.service.Impl.BookServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/index.html")
public class IndexServlet extends  ViewBaseServlet{
    private BookService bookService = new BookServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Book> books = bookService.getBooks();
        req.setAttribute("bookList",books);
        processTemplate("index",req,resp);
    }
}
