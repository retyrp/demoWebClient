package com.example.demoWebClient.foundation.service;

/**
 * @author YRP
 * @Title: InterfaceSerialNoService  交互流水生成服务
 * @date 2018/3/29 14:24
 */
public interface InterfaceSerialNoService {

    /** 加密返回数据流水号 */
    String EncryptedDataS(String serialNo);

    /** 解密返回流水号 */
    String DecryptDataS(String serialNo);

    /** 返回数据拼接 流水号生成 */
    String DataHandle(String oldSerialNo);
}
