package com.example.demoWebClient.foundation.service.impl;

import com.example.demoWebClient.foundation.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author YRP
 * @Title: RedisServiceImpl   Redis服务类
 * @date 2018/3/26 15:15
 */
@Service
public class RedisServiceImpl implements RedisService{

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Override
    public List<Map> callRedis(List<String> keys) {
        List<Map> maps = new ArrayList<>();
        Map map = new HashMap<>();
        try{
            for (String key:
                    keys) {
                String s = stringRedisTemplate.opsForValue().get(key);
                map.put(key,s);
                maps.add(map);
            }
            return maps;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int setRedis(List<Map> contents) {

        for (Map<String,String> temp: contents) {
            temp.forEach((key, value) -> {
                stringRedisTemplate.opsForValue().set(key,value);
            });
        }
        return contents.size();
    }

    @Override
    public int setRedis(List<Map> contents, long l, TimeUnit t) {
        for (Map<String,String> temp: contents) {
            temp.forEach((key, value) -> {
                stringRedisTemplate.opsForValue().set(key,value,l,t);
            });
        }
        return contents.size();
    }

    @Override
    public int setRedis(List<Map> contents, Date time) {
        for (Map<String,String> temp: contents) {
            temp.forEach((key, value) -> {
                stringRedisTemplate.opsForValue().set(key,value,time.getTime());
            });
        }
        return contents.size();
    }

    @Override
    public int delRedis(List<String> keys) {
        stringRedisTemplate.delete(keys);
        return keys.size();
    }

    @Override
    public int setTimeOutRedis(List<String> keys, long l, TimeUnit t) {
        return 0;
    }


}
