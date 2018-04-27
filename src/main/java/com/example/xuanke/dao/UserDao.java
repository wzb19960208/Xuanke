package com.example.xuanke.dao;

import com.example.xuanke.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface UserDao {

    @Select("select * from t_user where user_id = #{userId}")
    User getUserByUserId(@Param("userId") String userId);

    @Select("select * from t_user")
    List<User> getAllUser();


}
