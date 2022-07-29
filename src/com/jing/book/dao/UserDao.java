package com.jing.book.dao;

import com.jing.book.pojo.User;

public interface UserDao {
    void addUser(User user);

    long checkUser(String username);

    User login(User user);

}
