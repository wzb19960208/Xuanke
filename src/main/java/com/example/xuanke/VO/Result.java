package com.example.xuanke.VO;

import com.example.xuanke.domain.User;

public class Result<T> {

    Integer code;
    String msg;
    T data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static Result success(Object object){

        Result result = new Result<>();
        result.setCode(0);
        result.setMsg("成功");
        result.setData(object);
        return result;

    }

    public static Result fail(int code,String msg){

        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }



}
