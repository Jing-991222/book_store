package com.jing.book.service.Impl;

import com.jing.book.dao.Impl.UserDaoImpl;
import com.jing.book.dao.UserDao;
import com.jing.book.pojo.User;
import com.jing.book.service.UserService;
import com.jing.book.util.MD5Util;

public class UserServiceImpl implements UserService {
    private UserDao userDao = new UserDaoImpl();

    @Override
    public void addUser(User user) {
        user.setPassword(MD5Util.encode(user.getPassword()));
        userDao.addUser(user);
    }

    @Override
    public long checkUsername(String username) {
        return userDao.checkUser(username);
    }

    @Override
    public User login(User user) {
        user.setPassword(MD5Util.encode(user.getPassword()));
        return userDao.login(user);
    }
}
