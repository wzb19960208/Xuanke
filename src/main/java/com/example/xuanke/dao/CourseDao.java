package com.example.xuanke.dao;

import com.example.xuanke.domain.Course;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface CourseDao {

    @Select("select * from t_course where course_id = #{courseId} limit 1")
    public Course getCourseByCourseId(@Param("courseId") String courseId);

    @Select("select * from t_course")
    public List<Course> getAllCourse();

    @Update("update t_course set course_remain = #{courseRemain} where course_id = #{courseId}")
    int updateCourseRemain(Course course);

    @Update("update t_course set course_remain = course_remain - 1 where course_id = #{courseId} and course_remain >0")
    int sub(@Param("courseId")String courseId);

}
