package com.jing.book.service.Impl;

import com.jing.book.dao.CartDao;
import com.jing.book.dao.Impl.CartDaoImpl;
import com.jing.book.pojo.Book;
import com.jing.book.pojo.Cart;
import com.jing.book.pojo.Item;
import com.jing.book.service.CartService;

import java.math.BigDecimal;
import java.util.List;

public class CartServiceImpl implements CartService {

    private CartDao cartDao = new CartDaoImpl();


    @Override
    public void addBook(Integer userId, Integer bookId) {
        cartDao.addBook(userId,bookId);
    }

    @Override
    public long getTotalCount(Integer userId) {
        return cartDao.getTotalCount(userId);
    }

    @Override
    public Cart findItem(Integer userId, int bookId) {
        return cartDao.findItem(userId,bookId);
    }

    @Override
    public void updateCount(Cart cartItem) {
        cartItem.setCount(cartItem.getCount()+1);
        cartDao.updateItem(cartItem);
    }

    @Override
    public List<Item> findItemsByUserId(int userId) {
        return cartDao.findItemsByUserId(userId);
    }

    @Override
    public int removeItem(int id) {
        return cartDao.removeItem(id);
    }

    @Override
    public int clearCart(Integer id) {
        return cartDao.clearCart(id);
    }

    @Override
    public int updateCount(int id, int count) {
        return cartDao.updateItem(id,count);
    }
}
