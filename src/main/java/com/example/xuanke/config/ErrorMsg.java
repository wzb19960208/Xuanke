package com.example.xuanke.config;

//像Result的返回信息需要code和msg，单纯的静态变量，无法做到同时提供两个信息
public enum  ErrorMsg {

    USERID_EERROR(1,"学号格式出错"),PASSWORD_EERROR(1,"密码格式出错");


    private Integer code;
    private String msg;

    //必须提供构造方法
    ErrorMsg(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

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
}
