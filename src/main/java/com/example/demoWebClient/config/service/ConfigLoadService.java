package com.example.demoWebClient.config.service;

/**
 * @author YRP
 * @Title: ConfigLoadService 配置加载
 * @date 2018/3/20 9:11
 */
public interface ConfigLoadService {

    /**
     * 加载配置
     */
    void loadConfig();

    /**
     * 保存配置
     */
    void saveConfig();

    /**
     * 预览配置
     * @return
     */
    String viewConfig();

    /**
     * 从DB加载配置
     */
    void reLoadConfigDB();
}
