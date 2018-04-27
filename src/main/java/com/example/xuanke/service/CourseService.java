package com.example.xuanke.service;

import com.example.xuanke.config.ErrorCode;
import com.example.xuanke.dao.CourseDao;
import com.example.xuanke.domain.Course;
import com.example.xuanke.domain.User;
import com.example.xuanke.exception.GlobalException;
import com.example.xuanke.util.GsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;


@Service
public class CourseService {

    public static final Logger logger = LoggerFactory.getLogger(CourseService.class);

    @Autowired
    RedisService redisService;

    @Autowired
    CourseDao courseDao;

    public int sub(String courseId){
        return courseDao.sub(courseId);
    }

    public int updateCourseRemain(Course course){
        return courseDao.updateCourseRemain(course);
    }

    public Course getCourseByCourseId(String courseId){
        return courseDao.getCourseByCourseId(courseId);
    }

    /**
     * 从MySQL获取数据
     * @return
     */
    public List<Course> getAllCourse(){
        Long startTime = System.currentTimeMillis();
        List<Course> list = courseDao.getAllCourse();
        Long endTime = System.currentTimeMillis();
        logger.info("MySQL:"+(endTime-startTime)+"ms");
        return list;
    }

    /**
     * 从Redis获取数据
     * @return
     */
    public List<Course> getAllCourseFromRedis(){

        Long startTime = System.currentTimeMillis();

        List<Course> list = null;

        String data = (String) redisService.get("course_list");

        //已经略去转JSON的时间了
        Long endTime = System.currentTimeMillis();
        logger.info("Redis:"+(endTime-startTime)+"ms");

        if(data==null){
            list = getAllCourse();
            redisService.set("course_list",GsonUtil.toJson(list));
            return list;
        }else{
            list = (List<Course>) GsonUtil.fromJson(data,List.class);
            return list;
        }

    }

    public boolean showCourseList(String token,Model model){

        if(token==null){
            return false;
        }

        User user = (User) GsonUtil.fromJson((String) redisService.get(token),User.class);

        if(user==null){
            return false;
        }

        model.addAttribute("name",user.getUserName());

        return true;

    }

}
