package com.example.xuanke.service;

import com.example.xuanke.dao.RecordDao;
import com.example.xuanke.domain.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RecordService {

    @Autowired
    private RecordDao recordDao;

    public List<Record> getRecordByUserId(String userId){
        return recordDao.getRecordByUserId(userId);
    }

    public void insertOneRecord(Record record){
        recordDao.insertOneRecord(record);
    }

    @Transactional
    public void insertTwoeRecord(Record record1,Record record2){
        recordDao.insertOneRecord(record1);
        recordDao.insertOneRecord(record2);
    }



}
