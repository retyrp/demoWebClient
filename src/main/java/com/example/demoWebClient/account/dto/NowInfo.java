package com.example.demoWebClient.account.dto;

import java.sql.Timestamp;

/**
 * @author YRP
 * @Title: NowInfo  登录信息
 * @date 2018/3/30 10:20
 */
public class NowInfo {

    private String userId;      //用户ID
    private String interfaceKey;//交互Key
    private String deviceId;    //设备ID
    private Timestamp loginTime;//登录时间
    private Timestamp interfaceTime;//上次交互时间
    private Timestamp nextTime;//下次交互时间
    private String other;       //其它

    public Timestamp getNextTime() {
        return nextTime;
    }

    public void setNextTime(Timestamp nextTime) {
        this.nextTime = nextTime;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public String getUserId() {

        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getInterfaceKey() {
        return interfaceKey;
    }

    public void setInterfaceKey(String interfaceKey) {
        this.interfaceKey = interfaceKey;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public Timestamp getNextTimeKey() {
        return nextTime;
    }

    public void setNextTimeKey(Timestamp nextTimeKey) {
        this.nextTime = nextTimeKey;
    }

    public Timestamp getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Timestamp loginTime) {
        this.loginTime = loginTime;
    }

    public Timestamp getInterfaceTime() {
        return interfaceTime;
    }

    public void setInterfaceTime(Timestamp interfaceTime) {
        this.interfaceTime = interfaceTime;
    }
}
