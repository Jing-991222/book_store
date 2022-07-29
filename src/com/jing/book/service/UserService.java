package com.jing.book.service;

import com.jing.book.pojo.User;

public interface UserService {
    void addUser(User user);

    long checkUsername(String username);

    User login(User user);

}
