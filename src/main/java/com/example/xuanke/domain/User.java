package com.example.xuanke.domain;

public class User {

    /**
     * 驼峰命名必须和数据库下滑线命名一样，否则无法映射
     */
    private Integer userId;
    private String userName;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
