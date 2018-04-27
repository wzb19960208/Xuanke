package com.example.xuanke.service;

import com.example.xuanke.domain.Course;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.LinkedHashMap;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CourseServiceTest {

    @Autowired
    CourseService courseService;

    @Test
    public void getCourseByCourseId() {
        Course course = courseService.getCourseByCourseId("456");
        System.out.println(course);
    }

    @Test
    public void getAllCourse() {
        List<Course> list = courseService.getAllCourse();
        System.out.println(list);
    }

    @Test
    public void update(){

        Course course = new Course();
        course.setCourseId("123");
        course.setCourseRemain(500);
        courseService.updateCourseRemain(course);

    }
}