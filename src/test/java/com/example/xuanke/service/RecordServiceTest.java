package com.example.xuanke.service;

import com.example.xuanke.domain.Record;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RecordServiceTest {

    @Autowired
    RecordService recordService;

    @Test
    public void getRecordByUserId() {
        System.out.println(recordService.getRecordByUserId("123"));
    }

//    @Test
//    public void insert(){
//
//        Record record = new Record();
//        record.setUserId("123");
//        record.setCourseId("ghhhh");
//        recordService.insertOneRecord(record);
//
//    }

    @Test public void insertTwo(){
        Record record1 = new Record();
        record1.setUserId("123");
        record1.setCourseId("ghhhhgs");

        Record record2 = new Record();
        record2.setUserId("123");
        record2.setCourseId("ghhhhgggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggg");
        recordService.insertTwoeRecord(record1,record2);
    }

}