package com.example.xuanke.service;


import com.example.xuanke.config.ErrorCode;
import com.example.xuanke.exception.GlobalException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Service
public class RedisService{

    public static final Logger logger = LoggerFactory.getLogger(RedisService.class);

    @Autowired
    private JedisPool jedisPool;

    public void set(String key,String value){

        Jedis jedis = null;
        try{
            jedis = jedisPool.getResource();
            jedis.set(key,value);
        }catch (Exception ex){
            logger.error("Redis插入失败"+ex.toString());
            throw new GlobalException(ErrorCode.SERVER_ERROR,ex.getMessage());
        }finally {
            if(jedis!=null)
                jedis.close();
        }

    }

    public Object get(String key){
        Jedis jedis = null;
        try{
            jedis = jedisPool.getResource();
            return jedis.get(key);
        }catch (Exception ex){
            logger.error("Redis查询失败"+ex.toString());
            throw new GlobalException(ErrorCode.SERVER_ERROR,ex.getMessage());
        }finally {
            if(jedis!=null)
                jedis.close();
        }

    }


}
