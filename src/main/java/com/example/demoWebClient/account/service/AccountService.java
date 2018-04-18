package com.example.demoWebClient.account.service;

import com.alibaba.fastjson.JSONObject;
import com.example.demoWebClient.account.dto.Role;
import com.example.demoWebClient.foundation.dto.ResultData;

/**
 * @author YRP
 * @Title: AccountService 账户服务
 * @date 2018/3/30 10:54
 */
public interface AccountService {

    /**
     * 登录
     * @param r
     * @return
     */
    boolean signIn(Role r);

    /**
     * 注册
     * @param r
     * @return
     */
    ResultData signUp(JSONObject jsonObject);

    /**
     * 注销
     * @return
     */
    boolean logout(Role r);
}
