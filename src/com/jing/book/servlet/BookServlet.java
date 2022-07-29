package com.jing.book.servlet;

import com.jing.book.pojo.Book;
import com.jing.book.service.BookService;
import com.jing.book.service.Impl.BookServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

@WebServlet("/book")
public class BookServlet extends BaseServlet {

    private BookService bookService = new BookServiceImpl();

    /**
     * 管理书籍,展示所有书籍列表
     */
    protected void bookManager(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Book> books = bookService.getBooks();
        req.setAttribute("bookList",books);
        processTemplate("manager/book_manager",req,resp);
    }

    /**
     *跳转到修改书籍信息页面,并携带要修改书籍的信息
     */
    protected void toUpdateBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取要修改图书的id号
        int bookId = Integer.parseInt(req.getParameter("bookId"));
        //查找该图书,返回图书对象
        Book book = bookService.findBookById(bookId);
        //将图书对象存在request对象中 通过模板类携带到修改图书信息页面
        req.setAttribute("book",book);
        processTemplate("manager/book_edit",req,resp);
    }

    /**
     *修改图书信息
     */
    protected void updateBook(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        Book book = new Book();
        BeanUtils.populate(book,req.getParameterMap());
        bookService.updateBook(book);
        resp.sendRedirect(req.getContextPath() + "/book?method=bookManager");
    }

    /**
     * 前往添加图书页面
     */
    protected void toAddBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processTemplate("manager/book_add",req,resp);
    }


    /**
     * 添加图书
     */
    protected void addBook(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        Book book = new Book();
        BeanUtils.populate(book,req.getParameterMap());
        bookService.addBook(book);
        resp.sendRedirect(req.getContextPath() + "/book?method=bookManager");
    }

    /**
     * 删除图书
     */
    protected void deleteBook(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        int bookId = Integer.parseInt(req.getParameter("bookId"));
        bookService.deleteBook(bookId);
        resp.sendRedirect(req.getContextPath() + "/book?method=bookManager");
    }


}
