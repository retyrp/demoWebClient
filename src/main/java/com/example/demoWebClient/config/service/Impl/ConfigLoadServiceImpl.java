package com.example.demoWebClient.config.service.Impl;

import com.example.demoWebClient.config.dao.ConfigMapper;
import com.example.demoWebClient.config.dto.SystemConfig;
import com.example.demoWebClient.config.service.ConfigLoadService;
import com.example.demoWebClient.foundation.cache.CacheManager;
import com.example.demoWebClient.foundation.service.RedisService;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.*;


/**
 * @author YRP
 * @Title: ConfigLoadServiceImpl
 * @date 2018/3/20 11:18
 */
@Service
public class ConfigLoadServiceImpl implements ConfigLoadService {

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

        //*******************    get    ****************
        List<Map> result = null;
        List list = new ArrayList();
        list.add(name);
        result = callRedis(list);
        if (null != result.get(0).get(name))
            return result
                    .get(0)
                    .get(name)
                    .toString();

        Map map = new HashMap();
        map.put("s_key", name);
        result = callDB(map);

        //*********************   set  *****************

        String key = result.get(0).get("s_key").toString();
        String value = result.get(0).get("s_value").toString();
        if (StringUtils.isBlank(value))
            return null;
        result.clear();
        map.clear();
        map.put(key, value);
        result.add(map);
        setRedis(result);

        return value;
    }

    @Override
    public void saveConfig(List<Map> maps) {
        setRedis(maps);
        setDB(maps);
    }

    @Override
    public String viewConfig() {
        // load from DB
        List<Map> result = new ArrayList<Map>();
        Map map = new HashMap();
        try {
            callDB(null).forEach(l->{
                map.put(l.get("s_key"),l.get("s_value"));
            });
            result.add(map);
        } catch (Exception e) {
            e.printStackTrace();
            logger.warn("Warn,fail to load Data from database!");
        }
        // set Redis Data
        try{
            setRedis(result);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("fail cache into redis!");
        }

        // set Memory cache
        List<String> key = new ArrayList<>();
        key.add("SystemPrivateKey_A");
        key.add("SystemPrivateKey_B");
        key.add("SystemPrivateKey_C");
        key.add("SystemPrivateKey_D");
        key.add("SystemPrivateKey_E");
        key.add("SystemPrivateKey_F");
        key.add("SystemPrivateKey_G");
        key.add("SystemPrivateKey_H");
        key.add("SystemPrivateKey_I");
        key.add("SystemPrivateKey_J");
        key.add("SystemPrivateKey_K");
        key.add("SystemPrivateKey_L");
        key.add("SystemPrivateKey_M");
        key.add("SystemPrivateKey_N");
        key.add("SystemPrivateKey_O");
        List<Map> privateKeyList = callRedis(key);
        StringBuilder privateKey = new StringBuilder();

        privateKeyList.forEach(l -> {
            key.forEach(s->{
                if("SystemPrivateKey_O".equals(s))
                    privateKey.append(new StringBuffer(l.get(s).toString()).reverse().toString());
                else
                    privateKey.append(l.get(s).toString());
            });
        });
        CacheManager.putContent("PRIVATE_KEY", privateKey.toString());
        logger.info("--------------- ["+CacheManager.getContent("PRIVATE_KEY").getValue());
        List pKey = new ArrayList();
        StringBuilder publicKey = new StringBuilder();
        pKey.add("SystemPublicKey_A");
        pKey.add("SystemPublicKey_B");
        pKey.add("SystemPublicKey_C");
        pKey.add("SystemPublicKey_D");
        List<Map> publicKeyList = callRedis(pKey);
        publicKeyList.forEach(l->{
            pKey.forEach(s->{
                publicKey.append(l.get(s).toString());
            });
        });
        CacheManager.putContent("PUBLIC_KEY", publicKey.toString());
        logger.info("--------------- ["+CacheManager.getContent("PUBLIC_KEY").getValue());
        return "SUCCESS";
    }

    @Override
    public void reLoadConfigDB() {

    }


    /**
     * 加载 redis data
     *
     * @return
     */
    private List<Map> callRedis(List<String> keys) {
        return redisServiceImpl.callRedis(keys);
    }

    /**
     * 加载 DB data   | 默认更新所有配置
     *
     * @return
     */
    private List<Map> callDB(Map map) {

        List<Map> data = configMapper.loadConfig(map);
        if (null == data) {
            logger.warn("--[Can't find data from database,Something Wrong!]");
            return null;
        }
        return data;
    }

    /**
     * 写入redis
     *
     * @param contents
     */
    private void setRedis(List<Map> contents) {
        redisServiceImpl.setRedis(contents);
    }

    /**
     * 写入DB
     *
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
        } catch (Exception e) {
            e.printStackTrace();
            logger.warn("Fail to write into Database,please check the Service");
        }
    }
}
