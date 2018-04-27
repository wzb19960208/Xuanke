package com.example.xuanke.controller;

import com.example.xuanke.VO.LoginVO;
import com.example.xuanke.VO.Result;
import com.example.xuanke.config.ErrorMsg;
import com.example.xuanke.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Controller
@RequestMapping("/login")
public class LoginController {

    public static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    UserService userService;

    @RequestMapping("/to_login")
    public String toLogin(HttpServletRequest request){
        logger.info(request.getRequestURI());
        return "Login/login";
    }


    @RequestMapping(value = "/do_login",method = RequestMethod.POST)
    @ResponseBody
    public Result doLogin(@RequestBody LoginVO loginVO, HttpServletRequest request, HttpServletResponse response){
        logger.info(request.getRequestURI());
        logger.info(loginVO.toString());

        //登陆的验证逻辑，在Service里进行
        //失败的情况已经全局异常处理了，这里就不需要管失败的情况了
        userService.login(response,loginVO);

        try {
            //停10s，模拟网络延迟
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logger.info("返回数据");
        return Result.success(null);
    }

    @RequestMapping("/productlist")
    public String productList(HttpServletRequest request){
        logger.info(request.getRequestURI());
        return "Product/list";
    }

}
