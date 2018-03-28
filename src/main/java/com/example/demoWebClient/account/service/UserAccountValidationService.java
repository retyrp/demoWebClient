package com.example.demoWebClient.account.service;

import com.example.demoWebClient.account.dto.Role;

/**
 * 用户账户验证服务
 */
public interface UserAccountValidationService {
    /**
     * 服务进入许可
     * @param r
     * @return
     */
    boolean isAdmission(Role r);

    /**
     * 登录
     * @param r
     * @return
     */
    boolean login(Role r);

    /**
     * 登出
     * @param r
     * @return
     */
    boolean logout(Role r);

    /**
     * 根据UserId获取用户
     * @return
     */
    Role getUserByUserId(String userId);

}
