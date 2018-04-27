package com.example.xuanke.service;

import com.example.xuanke.VO.LoginVO;
import com.example.xuanke.VO.Result;
import com.example.xuanke.config.ErrorCode;
import com.example.xuanke.config.ErrorMsg;
import com.example.xuanke.dao.UserDao;
import com.example.xuanke.domain.User;
import com.example.xuanke.exception.GlobalException;
import com.example.xuanke.util.GsonUtil;
import com.example.xuanke.util.UUIDUtil;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Service
public class UserService {

    @Autowired
    UserDao userDao;

    @Autowired
    RedisService redisService;

    //返回Cookie
    public String login(HttpServletResponse response,LoginVO loginVO){

        /**
         * {
         "code": 1,
         "msg": "学号格式错误",
         "data": null
         }
         */
        //还是要检查参数，如果别人绕开前端，直接POST过来，就凉了
        if(loginVO.getUserId()==null||loginVO.getUserId().length()<6){
            throw new GlobalException(ErrorCode.USERID_NULL_ERROR,"学号格式错误");
        }

        if(loginVO.getPassword()==null||loginVO.getPassword().length()<6){
            throw new GlobalException(ErrorCode.PASSWORD_NULL_ERROR,"密码格式错误");
        }

        User user = null;

        //检查账号是否存在，密码是否正确
        //读数据库，我感觉必须trycath
        try {
            user = userDao.getUserByUserId(loginVO.getUserId());
        }catch (Exception ex){
            //如果catch到了异常，方法就不会继续执行了
            throw new GlobalException(ErrorCode.SERVER_ERROR,ex.getMessage());
        }

        if(user==null){
            throw new GlobalException(ErrorCode.USER_NOT_EXIST,"用户不存在");
        }

        if(!(loginVO.getPassword().equals(user.getUserPassword()))){
            throw new GlobalException(ErrorCode.PASSWORD_INCORRECT_ERROR,"密码不正确");
        }

        //登陆成功


        String token = UUIDUtil.uuid();
        addCookie(response,token,user);
        return token;

    }

    public User getUserByUserId(String userId){
        return userDao.getUserByUserId(userId);
    }

    public List<User> getAllUser(){
        return userDao.getAllUser();
    }

    public void addCookie(HttpServletResponse response,String token,User user){
        redisService.set(token, GsonUtil.toJson(user));
        Cookie cookie = new Cookie("token",token);
        //单位是s
        cookie.setMaxAge(60*10);
        cookie.setPath("/");
        response.addCookie(cookie);
    }

//    @Transactional
//    public boolean insertTwo(){
//
//        //不能捕捉异常，捕捉了就不会回滚了
//        User u1 = new User();
//        u1.setUserId(3);
//        u1.setUserName("xiaoxiao");
//        userDao.insert(u1);
//
//        User u2 = new User();
//        u2.setUserId(4);
//        //超过10位，会报异常
//        u2.setUserName("hhhhhhhhhhhhhhhh");
//        userDao.insert(u2);
//
//        return true;
//    }

}
