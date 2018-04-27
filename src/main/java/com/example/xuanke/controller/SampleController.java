package com.example.xuanke.controller;

import com.example.xuanke.VO.Result;
import com.example.xuanke.domain.User;
import com.example.xuanke.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping(value = "/Sample")
public class SampleController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/thymeleaf")
    public String thymeleaf(Model model){
        model.addAttribute("name","wzb");
        return "Sample/hello";
    }


    @RequestMapping(value = "/db/getAll")
    @ResponseBody
    public Result dbGetAll(){
        return Result.success(userService.getAllUser());
    }

//    @RequestMapping(value = "/db/insert")
//    @ResponseBody
//    public Result dbInsert(){
//        boolean b = userService.insertTwo();
//        if(b){
//            return Result.success(null);
//        }else {
//            return Result.fail(1,"插入两个user失败");
//        }
//    }


}
