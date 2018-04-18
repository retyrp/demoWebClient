package com.example.demoWebClient.account.dao;

import com.example.demoWebClient.account.dto.Role;
import com.example.demoWebClient.account.dto.RoleInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户操作
 */
@Repository
public interface RoleMapper {

    /** 登录（返回对应角色信息） */
    Role login(Role r);

    /** 获取信息 */
    RoleInfo getUserInfo(Role r);

    /** 注册 （注册关键信息 Role）*/
    int registered_a(Role r);

    /** 注册 （注册更多信息 RoleInfo） */
    int registered_b(RoleInfo ri);

    /** 获取用户列表（按Role） */
    List<Role> getUserListByRole(Role r);

    /** 获取用户列表（按RoleInfo） */
    List<Role> getUserListByRoleInfo(RoleInfo r);

    /** 更新（更新关键信息） */
    int update_a(Role r);

    /** 更新（更新更多信息）*/
    int update_b(RoleInfo ri);
}
