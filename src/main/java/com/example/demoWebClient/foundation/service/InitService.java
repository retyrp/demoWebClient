package com.example.demoWebClient.foundation.service;

import com.example.demoWebClient.config.service.ConfigLoadService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author YRP
 * @Title: InitService 初始化服务
 * @date 2018/3/30 15:19
 */
@Component
@Order(value = 1)
public class InitService implements CommandLineRunner {

    Logger logger = LoggerFactory.getLogger(InitService.class);

    @Autowired
    ConfigLoadService configLoadServiceImpl;

    @Autowired
    private AmqpTemplate rabbitTemplate;

    @Override
    public void run(String... strings) {
        logger.info(">>>>>---------------------------------------------------------------      14%--系统开始启动");
        logger.info(">>>>>>>>>>>>>-------------------------------------------------------      32%--加载基础服务");
        ConnectService();
        logger.info(">>>>>>>>>>>>>>>>>>>>>>>---------------------------------------------      48%--加载配置文件");
        loadConfig();
        logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>------------------------      70%--日志服务已启动");
        logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>--------------      80%--系统监测已启动");
        logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>《    100%--系统启动完成");
    }


    /**
     * 加载配置文件
     */
    private void loadConfig() {
        if ("SUCCESS".equals(configLoadServiceImpl.viewConfig()))
            logger.info("Over.....Success.");
    }

    /**
     * 连接服务
     */
    private void ConnectService() {
        logger.info("connect to DB....");
        if (StringUtils.isNotBlank(configLoadServiceImpl.loadConfig("SystemName")))
            logger.info("Success to connect Server Of DB");
        logger.info("connect to Rabbitmq...");
        rabbitTemplate.convertAndSend("[service]-hello");
    }
}
