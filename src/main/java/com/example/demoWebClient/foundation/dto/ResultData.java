package com.example.demoWebClient.foundation.dto;

import java.util.List;
import java.util.Map;

/**
 * @author YRP
 * @Title: ResultData   返回数据体标准
 * @date 2018/3/28 10:06
 */
public class ResultData {
    /** 交互流水号 */
    private String serialNo;
    /** 时间戳 */
    private String timeStamp;
    /** 信号标志 */
    private String sign;
    /** 数据内容 */
    private List<Map> data;


    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public List<Map> getData() {
        return data;
    }

    public void setData(List<Map> data) {
        this.data = data;
    }
}
