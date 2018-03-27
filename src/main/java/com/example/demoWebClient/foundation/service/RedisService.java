package com.example.demoWebClient.foundation.service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author YRP
 * @Title: RedisService  -About Redis Service
 * @date 2018/3/23 10:50
 */
public interface RedisService {

    /**
     * 加载 redis data
     * @param keys 关键字
     * @return
     */
    List<Map> callRedis(List<String> keys);

    /**
     * 设置 redis data
     * @param contents  关键字+内容
     * @return
     */
    int setRedis(List<Map> contents);

    /**
     * 设置 redis data
     * @param contents  关键字+内容   时长  时间单位
     * @param l
     * @param t
     * @return
     */
    int setRedis(List<Map> contents, long l, TimeUnit t);

    /**
     * 设置 redis data
     * @param contents  关键字+内容  时间点
     * @param time
     * @return
     */
    int setRedis(List<Map> contents,Date time);

    /**
     * 删除  redis data
     * @param keys
     * @return
     */
    int delRedis(List<String> keys);

    int setTimeOutRedis(List<String> keys,long l,TimeUnit t);


}
