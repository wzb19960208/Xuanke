package com.example.xuanke.service;

import com.example.xuanke.config.ErrorCode;
import com.example.xuanke.domain.Course;
import com.example.xuanke.domain.Record;
import com.example.xuanke.domain.User;
import com.example.xuanke.exception.GlobalException;
import jdk.nashorn.internal.objects.Global;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.Date;
import java.util.List;

@Service
public class MiaoshaService{

    public static final Logger logger = LoggerFactory.getLogger(MiaoshaService.class);

    public static int count=0;
    public static int len = 100;


    @Autowired
    CourseService courseService;

    @Autowired
    UserService userService;

    @Autowired
    RecordService recordService;



    public synchronized void add(){

        if(len<=0){
            throw new GlobalException(1111,"len==0");
        }

        len--;
        count++;
        logger.info("len:"+len);
        logger.info("count:"+count);

    }

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    private int saveAndSub(String userId,String courseId){

        //取课程
        Course course = courseService.getCourseByCourseId(courseId);
//        //判断库存
        if (course.getCourseRemain() <= 0) {
            //上面只有读操作，不需要回滚
            return 0;
        }
        //扣库存
        course.setCourseRemain(course.getCourseRemain() - 1);
        int result = courseService.updateCourseRemain(course);
        //int result = courseService.sub(courseId);

        //还需要上面的步骤吗？
        //直接在减库存的时候判断不就ok?
        //输出结果，count表示抢到课程的人数
        if(result!=0){
            count++;
            //如果减成功，填一条记录
            Record record = new Record();
            record.setUserId(userId);
            record.setCourseId(courseId);
            record.setRecordTime(new Date());
            recordService.insertOneRecord(record);
            return 1;
        }


//        logger.info("CourseRemain:" + course.getCourseRemain());
//        logger.info("success:"+result);
        logger.info("Count:" + count);
        return 0;

    }

    public int  miaosha(String userId,String courseId){



        //假设抢单事务得30s
        try {
            Thread.sleep(40000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        int result = saveAndSub(userId,courseId);

        return result;

    }



}
