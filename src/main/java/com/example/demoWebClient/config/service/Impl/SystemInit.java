package com.example.demoWebClient.config.service.Impl;

import com.example.demoWebClient.config.service.InitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;



/**
 * @author YRP
 * @Title: SystemInit 系统初始化
 * @date 2018/3/20 11:07
 */
public class SystemInit implements InitService{

    @Autowired
    protected RedisTemplate redisTemplate;

    @Override
    public void init() {

    }

    @Override
    public void load() {
    }

    @Override
    public void refresh() {

    }

    @Override
    public void recycler() {

    }


    private void loadRedis(){
        redisTemplate.opsForValue();
    }

    private void loadDB(){}
}
