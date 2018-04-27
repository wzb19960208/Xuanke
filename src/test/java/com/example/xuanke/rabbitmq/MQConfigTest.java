package com.example.xuanke.rabbitmq;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MQConfigTest {

    @Autowired
    MQSender mqSender;

    @Autowired
    MQReceiver mqReceiver;

    @Test
    public void send(){
        mqSender.send("hello word!");
    }



}