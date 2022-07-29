package com.jing.book.dao.Impl;

import com.jing.book.dao.CartDao;
import com.jing.book.pojo.Cart;
import com.jing.book.pojo.Item;

import java.math.BigDecimal;
import java.util.List;

public class CartDaoImpl extends BaseDaoImpl implements CartDao {

    @Override
    public void addBook(Integer userId, Integer bookId) {
        String sql = "insert into t_cart values(null,?,1,?)";
        update(sql,bookId,userId);
    }

    @Override
    public int updateItem(int id, int count) {
        String sql = "update t_cart set count=? where id=?";
        return update(sql,count,id);
    }

    @Override
    public void updateItem(Cart cartItem) {
        String sql = "update t_cart set count=? where id=?";
        update(sql,cartItem.getCount(),cartItem.getId());
    }


    @Override
    public Cart findItem(Integer userId, int bookId) {
        String sql = "select id,book_id bookId,count,user_id userId from t_cart where book_id = ? and user_id = ?";
        return getBean(Cart.class,sql,bookId,userId);
    }


    @Override
    public long getTotalCount(Integer userId) {
        String sql = "select sum(count) from t_cart where user_id=?";
        BigDecimal value = (BigDecimal) getValue(sql, userId);
        if (value == null){
            return 0;
        }
        return value.longValue();
    }

    @Override
    public List<Item> findItemsByUserId(int userId) {
        //SELECT c.id id,c.book_id book_id,c.count count,c.user_id user_id,b.title title,b.img_path img_path,b.price price FROM
        // `t_cart` c JOIN books b WHERE c.book_id = b.id HAVING c.user_id = 30
        String sql = "SELECT c.id id,c.book_id bookId,c.count count,c.user_id userId,b.title title,b.img_path imgPath,b.price price FROM t_cart c JOIN books b WHERE c.book_id=b.id HAVING c.user_id=?";
        return getList(Item.class,sql,userId);
    }

    @Override
    public int removeItem(int id) {
        String sql = "delete from t_cart where id=?";
        return update(sql,id);
    }

    @Override
    public int clearCart(Integer id) {
        String sql = "delete from t_cart where user_id = ?";
        return update(sql,id);
    }


}
