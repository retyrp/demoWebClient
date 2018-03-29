package com.example.demoWebClient.config.service.Impl;

import com.example.demoWebClient.config.dao.ConfigMapper;
import com.example.demoWebClient.config.dto.SystemConfig;
import com.example.demoWebClient.config.service.ConfigLoadService;
import com.example.demoWebClient.foundation.service.RedisService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author YRP
 * @Title: ConfigLoadServiceImpl
 * @date 2018/3/20 11:18
 */
@Service
public class ConfigLoadServiceImpl implements ConfigLoadService{

    protected static Logger logger = Logger.getLogger(ConfigLoadServiceImpl.class);

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Autowired
    ConfigMapper configMapper;

    @Autowired
    RedisService redisServiceImpl;

    private static SystemConfig systemConfig;

    @Override
    public String loadConfig(String name) {
        List<Map> result = null;
        List list = new ArrayList();
        list.add(name);
        result = callRedis(list);
        if(!result.isEmpty())
            return result
                    .iterator()
                    .next()
                    .get("s_value")
                    .toString();

        Map map = new HashMap();
        map.put("s_key",name);
        result = callDB(map);
        if(result.isEmpty())
            return null;
        setRedis(result);
        return result
                .iterator()
                .next()
                .get("s_value")
                .toString();
    }

    @Override
    public void saveConfig(List<Map> maps) {
        setRedis(maps);
        setDB(maps);
    }

    @Override
    public String viewConfig() {
        return null;
    }

    @Override
    public void reLoadConfigDB() {

    }


    /**
     * 加载 redis data
     * @return
     */
    private List<Map> callRedis(List<String> keys){
        return redisServiceImpl.callRedis(keys);
    }

    /**
     * 加载 DB data   | 默认更新所有配置
     * @return
     */
    private List<Map> callDB(Map map){

        List<Map> data = configMapper.loadConfig(map);
        if(null == data){
            logger.warn("--[Can't find data from database,Something Wrong!]");
            return null;
        }
        return data;
    }

    /**
     * 写入redis
     * @param contents
     */
    private void setRedis(List<Map> contents){
        redisServiceImpl.setRedis(contents);
    }

    /**
     * 写入DB
     * @param contents 三层更新  'update value as key'
     */
    private void setDB(List<Map> contents) {
        Map map = new HashMap();
        try {
            for (Map temp : contents) {
                temp.forEach((key, value) -> {
                    map.put(key, value);
                    if (configMapper.updateConfig(map) < 1)
                        configMapper.insertConfig(map);         //找不到就插入一条，确保信息可读
                });
            }
        }catch (Exception e){
            e.printStackTrace();
            logger.warn("Fail to write into Database,please check the Service");
        }
    }
}
