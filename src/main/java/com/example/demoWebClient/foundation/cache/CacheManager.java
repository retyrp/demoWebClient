package com.example.demoWebClient.foundation.cache;

import java.util.Date;
import java.util.HashMap;

/**
 * @author YRP
 * @Title: CacheManager 缓存管理（Memory storage Manager）
 * @date 2018/3/20 9:47
 */
public class CacheManager {

    private static HashMap cacheMap = new HashMap();

    private CacheManager (){}

    private synchronized  static Cache getCache(String key){
        return (Cache)cacheMap.get(key);
    }

    private synchronized static boolean hasCache(String key){
        return cacheMap.containsKey(key);
    }

    public synchronized static void invalidateAll(){
        cacheMap.clear();
    }

    public synchronized static void invalidate(String key){
        cacheMap.remove(key);
    }

    private synchronized static void putCache(String key,Cache object){
        cacheMap.put(key,object);
    }

    private static boolean cacheExpired(Cache cache){
        if(cache == null)
            return false;

        long milisNow = new Date().getTime();
        long milisExpire = cache.getTimeOut();
        if(milisExpire < 0){
            return false;
        }else if(milisNow>= milisExpire){
            return true;
        }else{
            return false;
        }
    }

    public static Cache getContent(String key){
        if(hasCache(key)){
            Cache cache = getCache(key);
            if(cacheExpired(cache))
                cache.setExpired(true);
            return cache;
        }else{
            return null;
        }
    }

    public static void putContent(String key,Object content,long ttl){
        Cache cache = new Cache();
        cache.setKey(key);
        cache.setValue(content);
        cache.setTimeOut(ttl = new Date().getTime());
        cache.setExpired(false);
        putCache(key,cache);
    }

    public static void putContent(String key,Object content){
        Cache cache = new Cache();
        cache.setKey(key);
        cache.setValue(content);
        cache.setExpired(false);
        putCache(key,cache);
    }

}
