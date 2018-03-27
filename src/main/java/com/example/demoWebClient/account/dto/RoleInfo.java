package com.example.demoWebClient.account.dto;

/**
 * 用户角色信息
 */
public class RoleInfo {
    /** DTO_1 */
    private String serialNo;                        //流水号
    private String userPhone;                       //电话
    private String userMail;                        //邮箱
    private String userClass;                       //班级
    private String userSchool;                      //学院信息
    private String userUniversity;                  //大学
    private String userRoom;                        //宿舍
    private String userSex;                         //性别
    private String userTencentAccount;              //Tx
    private String userWeibo;                       //微博


    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserMail() {
        return userMail;
    }

    public void setUserMail(String userMail) {
        this.userMail = userMail;
    }

    public String getUserClass() {
        return userClass;
    }

    public void setUserClass(String userClass) {
        this.userClass = userClass;
    }

    public String getUserSchool() {
        return userSchool;
    }

    public void setUserSchool(String userSchool) {
        this.userSchool = userSchool;
    }

    public String getUserUniversity() {
        return userUniversity;
    }

    public void setUserUniversity(String userUniversity) {
        this.userUniversity = userUniversity;
    }

    public String getUserRoom() {
        return userRoom;
    }

    public void setUserRoom(String userRoom) {
        this.userRoom = userRoom;
    }

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }

    public String getUserTencentAccount() {
        return userTencentAccount;
    }

    public void setUserTencentAccount(String userTencentAccount) {
        this.userTencentAccount = userTencentAccount;
    }

    public String getUserWeibo() {
        return userWeibo;
    }

    public void setUserWeibo(String userWeibo) {
        this.userWeibo = userWeibo;
    }
}
