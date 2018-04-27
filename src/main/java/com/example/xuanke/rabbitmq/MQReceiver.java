package com.example.xuanke.rabbitmq;

import com.example.xuanke.domain.Record;
import com.example.xuanke.service.MiaoshaService;
import com.example.xuanke.util.GsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MQReceiver {

    private static final Logger logger = LoggerFactory.getLogger(MQReceiver.class);

    @Autowired
    MiaoshaService miaoshaService;

    /**
     * 这是一个listener，不是主动调用的。总之队伍里面有消息，就会自动去取了
     *
     * Created new connection: rabbitConnectionFactory#54d901aa:0/SimpleConnection@48132d8e [delegate=amqp://guest@192.168.80.128:5672/, localPort= 58180]
     2018-04-15 11:07:38.125  INFO 53364 --- [           main] c.example.xuanke.rabbitmq.MQConfigTest   : Started MQConfigTest in 4.929 seconds (JVM running for 6.346)
     2018-04-15 11:07:38.219  INFO 53364 --- [           main] com.example.xuanke.rabbitmq.MQSender     : send msg:"hello word!"
     2018-04-15 11:07:38.233  INFO 53364 --- [       Thread-2] o.s.w.c.s.GenericWebApplicationContext   : Closing org.springframework.web.context.support.GenericWebApplicationContext@19b843ba: startup date [Sun Apr 15 11:07:33 CST 2018]; root of context hierarchy
     2018-04-15 11:07:38.236  INFO 53364 --- [       Thread-2] o.s.c.support.DefaultLifecycleProcessor  : Stopping beans in phase 2147483647
     2018-04-15 11:07:38.238  INFO 53364 --- [cTaskExecutor-5] com.example.xuanke.rabbitmq.MQReceiver   : receive msg:"hello word!"
     2018-04-15 11:07:38.245  INFO 53364 --- [       Thread-2] o.s.a.r.l.SimpleMessageListenerContainer : Waiting for workers to finish.
     2018-04-15 11:07:39.641  INFO 53364 --- [       Thread-2] o.s.a.r.l.SimpleMessageListenerContainer : Successfully waited for workers to finish.
     2018-04-15 11:07:39.642  INFO 53364 --- [       Thread-2] o.s.c.support.DefaultLifecycleProcessor  : Stopping beans in phase 0
     2018-04-15 11:07:39.648  INFO 53364 --- [       Thread-2] o.s.a.r.l.SimpleMessageListenerContainer : Shutdown ignored - container is not active already
     * @param message
     */
    @RabbitListener(queues = MQConfig.QUEUE)
    public void receive(String message){
        Record record = (Record) GsonUtil.fromJson(message,Record.class);
        miaoshaService.miaosha(record.getUserId(),record.getCourseId());
        logger.info("receive msg:"+message);
    }

}
