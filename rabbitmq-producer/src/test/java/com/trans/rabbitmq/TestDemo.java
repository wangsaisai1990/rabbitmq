package com.trans.rabbitmq;

import com.trans.rabbitmq.service.MessageServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestDemo {

    @Autowired
    private MessageServiceImpl messageService;

    @Test
    public void send() {
        for(int i=0;i<10;i++){
            messageService.sendMsg("test_queue_1","hello i am delay msg"+i);
        }
    }

}