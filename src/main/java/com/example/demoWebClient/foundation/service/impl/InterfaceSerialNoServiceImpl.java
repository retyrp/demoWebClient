package com.example.demoWebClient.foundation.service.impl;

import com.example.demoWebClient.foundation.service.InterfaceSerialNoService;
import com.example.demoWebClient.foundation.service.MD5Factory;
import com.example.demoWebClient.foundation.service.TimeFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author YRP
 * @Title: InterfaceSerialNoServiceImpl
 * @date 2018/3/29 14:35
 */
public class InterfaceSerialNoServiceImpl implements InterfaceSerialNoService {

    private static final String INTERFACE_KEY = "InterfaceKey";

    @Autowired
    MD5Factory md5Factory;

    @Autowired
    TimeFactory timeFactory;

    @Override
    public String EncryptedDataS(String serialNo) {
        //解析接收到的报文
        serialNo.substring( serialNo.length());
        timeFactory.getSqlTime();return null;
    }

    @Override
    public String DecryptDataS(String serialNo) {
        return null;
    }

    @Override
    public String DataHandle(String oldSerialNo) {
        return null;
    }
}
