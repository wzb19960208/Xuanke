package com.example.xuanke.controller;

import com.example.xuanke.VO.Result;
import com.example.xuanke.domain.Record;
import com.example.xuanke.rabbitmq.MQSender;
import com.example.xuanke.service.MiaoshaService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class MiaoshaController implements InitializingBean{

    /**
     * 加载该类的时候会调用
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        //加载库存到Redis上去
    }

    @Autowired
    MiaoshaService miaoshaService;

    @Autowired
    MQSender mqSender;

    @RequestMapping(value = "/miaosha",method = RequestMethod.POST)
    @ResponseBody
    public  Result miaosha(@RequestParam("userId") String userId, @RequestParam("courseId") String courseId){
//        System.out.println(request.getParameterMap().keySet());
//        System.out.println(userId);
//        System.out.println(courseId);
        //int result = miaoshaService.miaosha(userId,courseId);

        //入队
        Record record = new Record();
        record.setUserId(userId);
        record.setCourseId(courseId);
        mqSender.send(record);

        //立刻返回
        return Result.fail(1,"排队中");
    }

    @RequestMapping(value = "/add")
    @ResponseBody
    public Result add(){
        miaoshaService.add();
        return Result.success(1);
    }



}
