package com.example.xuanke.domain;

import java.util.Date;
import java.util.List;

public class Record {

    private String userId;
    private String  courseId;
    private Date recordTime;

    public Record() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public Date getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(Date recordTime) {
        this.recordTime = recordTime;
    }

    @Override
    public String toString() {
        return "Record{" +
                "userId='" + userId + '\'' +
                ", courseId='" + courseId + '\'' +
                '}';
    }
}
