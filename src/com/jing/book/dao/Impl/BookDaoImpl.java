package com.jing.book.dao.Impl;

import com.jing.book.dao.BookDao;
import com.jing.book.pojo.Book;

import java.util.List;

public class BookDaoImpl extends BaseDaoImpl implements BookDao {
    @Override
    public List<Book> getBooks() {
        String sql = "select id,title,author,price,sales,stock,img_path imgPath from books";
        return getList(Book.class,sql);
    }

    @Override
    public Book findBookById(int bookId) {
        String sql = "select id,title,author,price,sales,stock,img_path imgPath from books where id=?";
        return getBean(Book.class,sql,bookId);
    }

    @Override
    public void updateBook(Book book) {
        String sql = "update books set title=?,author=?,price=?,sales=?,stock=?,img_path=? where id=?";
        update(sql,book.getTitle(),
                book.getAuthor(),
                book.getPrice(),
                book.getSales(),
                book.getStock(),
                book.getImgPath(),
                book.getId());
    }

    @Override
    public void addBook(Book book) {
        String sql = "insert into books values(null,?,?,?,?,?,?)";
        update(sql,book.getTitle(),
                book.getAuthor(),
                book.getPrice(),
                book.getSales(),
                book.getStock(),
                book.getImgPath());
    }

    @Override
    public void deleteBook(int bookId) {
        String sql = "delete from books where id=?";
        update(sql,bookId);
    }
}
