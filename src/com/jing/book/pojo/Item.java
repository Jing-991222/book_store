package com.jing.book.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Item implements Serializable {
    /**
     * c.id id,c.book_id book_id,c.count count,c.user_id user_id,b.title title,b.img_path img_path,b.price price
     */

    private Integer id;

    private Integer bookId;

    private Integer count;

    private Integer userId;

    private String title;

    private String imgPath;

    private Double price;

    private Double amount;


}
