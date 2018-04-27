package com.example.xuanke.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisServiceTest {

    public static final Logger logger = LoggerFactory.getLogger(RedisServiceTest.class);

    @Autowired
    RedisService redisService;

    @Test
    public void set() {
        redisService.set("weizhibin","boyboy");
    }

    @Test
    public void get() {
        String str = (String) redisService.get("7d912ce9e2ab4d34891e03710156e3b3");
        logger.info(str);
    }
}