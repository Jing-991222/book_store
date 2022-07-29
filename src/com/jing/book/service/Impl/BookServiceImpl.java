package com.jing.book.service.Impl;

import com.jing.book.dao.BookDao;
import com.jing.book.dao.Impl.BookDaoImpl;
import com.jing.book.pojo.Book;
import com.jing.book.service.BookService;

import java.util.List;

public class BookServiceImpl implements BookService {
    private BookDao bookDao = new BookDaoImpl();
    @Override
    public List<Book> getBooks() {
        return bookDao.getBooks();
    }

    @Override
    public Book findBookById(int bookId) {
        return bookDao.findBookById(bookId);
    }

    @Override
    public void updateBook(Book book) {
        bookDao.updateBook(book);
    }

    @Override
    public void addBook(Book book) {
        bookDao.addBook(book);
    }

    @Override
    public void deleteBook(int bookId) {
        bookDao.deleteBook(bookId);
    }
}
