package com.example.xuanke.domain;

public class CourseDTO {

    private String courseId;

    public CourseDTO() {
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    @Override
    public String toString() {
        return "CourseDTO{" +
                "courseId='" + courseId + '\'' +
                '}';
    }
}
