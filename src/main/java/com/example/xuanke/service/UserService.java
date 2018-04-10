package com.example.xuanke.service;

import com.example.xuanke.dao.UserDao;
import com.example.xuanke.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
public class UserService {

    @Autowired
    UserDao userDao;

    public User getUserById(int id){
        System.out.println(userDao.getUserById(id));
        return  userDao.getUserById(id);
    }

    public List<User> getAllUser(){
        return userDao.getAllUser();
    }

    @Transactional
    public boolean insertTwo(){

        //不能捕捉异常，捕捉了就不会回滚了
        User u1 = new User();
        u1.setUserId(3);
        u1.setUserName("xiaoxiao");
        userDao.insert(u1);

        User u2 = new User();
        u2.setUserId(4);
        //超过10位，会报异常
        u2.setUserName("hhhhhhhhhhhhhhhh");
        userDao.insert(u2);

        return true;
    }

}
