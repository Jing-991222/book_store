package com.jing.book.dao;

import com.jing.book.pojo.Book;

import java.util.List;

public interface BookDao {
    List<Book> getBooks();

    Book findBookById(int bookId);

    void updateBook(Book book);

    void addBook(Book book);

    void deleteBook(int bookId);
}
