package com.example.demoWebClient.account.dto;

/**
 * 用户权限控制
 */
public class UAC {

    private String RoleID;            //角色ID
    private String RoleName;          //角色名称
    private String RoleKind;          //角色种类
    private String RoleBeginDate;     //角色开始时间
    private String RoleEndDate;       //角色结束时间
    private String RoleU;             //角色等级

    public String getRoleID() {
        return RoleID;
    }

    public void setRoleID(String roleID) {
        RoleID = roleID;
    }

    public String getRoleName() {
        return RoleName;
    }

    public void setRoleName(String roleName) {
        RoleName = roleName;
    }

    public String getRoleKind() {
        return RoleKind;
    }

    public void setRoleKind(String roleKind) {
        RoleKind = roleKind;
    }

    public String getRoleBeginDate() {
        return RoleBeginDate;
    }

    public void setRoleBeginDate(String roleBeginDate) {
        RoleBeginDate = roleBeginDate;
    }

    public String getRoleEndDate() {
        return RoleEndDate;
    }

    public void setRoleEndDate(String roleEndDate) {
        RoleEndDate = roleEndDate;
    }

    public String getRoleU() {
        return RoleU;
    }

    public void setRoleU(String roleU) {
        RoleU = roleU;
    }

}
