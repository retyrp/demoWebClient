package com.example.demoWebClient.account.service.Impl;

import com.example.demoWebClient.account.dto.Role;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RabbitListener(queues = "hello")
public class UserLoginHandle {

    @RabbitHandler
    public void process(String hello){
        System.out.println("--Receiver:"+hello);
    }
    public boolean login(Role r){
        return false;
    }
}
