package com.example.demoWebClient.account.dto;

import javax.annotation.sql.DataSourceDefinition;

/**
 *  用户角色
 */
public class Role {

    private String UID;                    //用户ID
    private String userPassWord;           //Password
    private String userName;               //姓名
    private String userRole;               //用户角色
    private String userInfo;               //用户信息

    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }

    public String getUserPassWord() {
        return userPassWord;
    }

    public void setUserPassWord(String userPassWord) {
        this.userPassWord = userPassWord;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public String getUserInfo() { return userInfo;    }

    public void setUserInfo(String userInfo) { this.userInfo = userInfo; }
}
