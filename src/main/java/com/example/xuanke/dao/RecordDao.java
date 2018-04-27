package com.example.xuanke.dao;

import com.example.xuanke.domain.Record;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface RecordDao {

    @Select("select user_id,course_id from t_record where user_id = #{userId}")
    public List<Record> getRecordByUserId(@Param("userId")String userId);

    @Insert("insert into t_record(user_id,course_id,record_time) values(#{userId},#{courseId},#{recordTime})")
    public void insertOneRecord(Record record);

}
