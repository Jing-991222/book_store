package com.jing.book.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cart implements Serializable {

    private Integer id;

    private Integer bookId;

    private Integer count;

    private Integer userId;


}
