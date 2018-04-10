package com.example.demoWebClient.foundation.dto;

import com.alibaba.fastjson.JSONObject;
import com.example.demoWebClient.foundation.service.RSAFactory;
import org.apache.commons.codec.binary.Base64;
import sun.misc.BASE64Decoder;

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
    /** 签名 */
    private String sign;
    /** 数据内容 */
    private List<Map> data;

    /**
     * 转JSON格式
     * @return
     */
    public JSONObject toJSONObject(){
        StringBuilder sb = new StringBuilder();
        sb.append("{'serialNo':'")
                .append(this.serialNo)
                .append("','timeStamp':'")
                .append(this.timeStamp)
                .append("','sign':'")
                .append(this.sign).append("','data':[");
        data.forEach(map -> {
            sb.append("{");
            map.forEach((key,value)->{
                sb.append("'").append(key).append("':'").append(value).append("',");
            });
            sb.delete(sb.length()-1,sb.length());
            sb.append("}");
        });
        sb.append("]}");
        System.out.println(sb);
        return JSONObject.parseObject(sb.toString());
    }

    /**
     * 签名
     * @return
     * @throws Exception
     */
    public void sign() throws Exception {
        setSign(Base64.encodeBase64String(RSAFactory.sign(new BASE64Decoder().decodeBuffer(toJSONObject().getString("data")))));
    }

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
