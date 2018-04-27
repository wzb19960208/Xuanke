package com.example.xuanke.controller;

import com.example.xuanke.VO.Result;
import com.example.xuanke.domain.Course;
import com.example.xuanke.service.CourseService;
import com.example.xuanke.util.JMeterUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/course")
public class CourseController {

    @Autowired
    CourseService courseService;

    @RequestMapping("/list")
    public String list(@CookieValue(value = "token",required = false)String cookieToken ,Model model){

        //boolean result = courseService.showCourseList(cookieToken,model);

        //压测
        boolean result = true;

        if(result){
            return "Course/list";
        }else {
            return "redirect:/login/to_login";
        }


    }

    @RequestMapping("/show_list")
    @ResponseBody
    public Result showList(){

        List<Course> list = courseService.getAllCourse();
        return Result.success(list);

    }

    @RequestMapping("/show_list_from_redis")
    @ResponseBody
    public Result showListFromRedis(){

        List<Course> list = courseService.getAllCourseFromRedis();
        return Result.success(list);

    }


}
