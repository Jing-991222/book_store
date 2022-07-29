package com.jing.book.dao;

import com.jing.book.pojo.Cart;
import com.jing.book.pojo.Item;

import java.util.List;

public interface CartDao {

    Cart findItem(Integer userId, int bookId);


    void updateItem(Cart cartItem);

    long getTotalCount(Integer userId);

    List<Item> findItemsByUserId(int userId);

    int removeItem(int id);

    int clearCart(Integer id);

    void addBook(Integer userId, Integer bookId);

    int updateItem(int id, int count);
}
