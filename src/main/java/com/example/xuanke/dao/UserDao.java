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

    @Select("select * from t_user where user_id = #{id}")
    User getUserById(@Param("id") int id);

    @Select("select * from t_user")
    List<User> getAllUser();

    @Insert("insert into t_user(user_id,user_name) values(#{userId},#{userName})")
    int insert(User user);
}
