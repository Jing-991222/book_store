package com.jing.book.service;

import com.jing.book.pojo.Book;
import com.jing.book.pojo.Cart;
import com.jing.book.pojo.Item;

import java.util.List;

public interface CartService {

    void addBook(Integer userId, Integer bookId);

    long getTotalCount(Integer userId);

    Cart findItem(Integer userId, int bookId);

    void updateCount(Cart cartItem);

    List<Item> findItemsByUserId(int userId);

    int removeItem(int id);

    int clearCart(Integer id);

    int updateCount(int id, int count);
}
