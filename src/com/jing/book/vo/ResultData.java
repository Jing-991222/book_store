package com.jing.book.vo;

import java.io.Serializable;

public class ResultData implements Serializable {


    private Boolean flag;
    private String msg;
    private Object data;

    private ResultData() {
    }

    private ResultData(Boolean flag, String msg, Object data) {
        this.flag = flag;
        this.msg = msg;
        this.data = data;
    }

    public Boolean getFlag() {
        return flag;
    }

    public void setFlag() {
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }



    public static ResultData fail(){
        return new ResultData(false,"请求失败",null);
    }

    public static ResultData success(){
        return new ResultData(true,"请求成功",null);
    }

    public static ResultData success(Object data){
        return new ResultData(true,"请求成功",data);
    }

    public static ResultData success(String msg,Object data){
        return new ResultData(true,msg,data);
    }

    @Override
    public String toString() {
        return "Result{" +
                "flag=" + flag +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
