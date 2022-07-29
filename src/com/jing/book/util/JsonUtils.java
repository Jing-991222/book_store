package com.jing.book.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jing.book.vo.ResultData;

public class JsonUtils {


    /**
     * 返回一个json
     * @param object
     * @return
     */
    public static String json(Object object){

        String json = null;
        try {
            json = new ObjectMapper().writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return json;
    }
}
