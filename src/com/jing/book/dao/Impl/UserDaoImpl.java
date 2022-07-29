package com.jing.book.dao.Impl;

import com.jing.book.dao.UserDao;
import com.jing.book.pojo.User;

public class UserDaoImpl extends BaseDaoImpl implements UserDao {

    @Override
    public void addUser(User user) {
        String sql = "insert into user values(null,?,?,?)";
        update(sql,user.getUsername(),user.getPassword(),user.getEmail());
    }

    @Override
    public long checkUser(String username) {
        String sql = "select count(0) from user where username = ?";
        return (long) getValue(sql,username);
    }

    @Override
    public User login(User user) {
        String sql = "select * from user where username = ? and password = ?";
        return getBean(User.class,sql,user.getUsername(),user.getPassword());
    }
}
