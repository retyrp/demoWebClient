package com.example.demoWebClient.account.service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.example.demoWebClient.account.dao.RoleMapper;
import com.example.demoWebClient.account.dto.Role;
import com.example.demoWebClient.account.service.AccountService;
import com.example.demoWebClient.foundation.dto.ResultData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    RoleMapper roleMapper;
    @Autowired
    public BCryptPasswordEncoder passwordEncoder() { return new BCryptPasswordEncoder();    }

    @Override
    public boolean signIn(Role r) {
        return false;
    }

    @Override
    public ResultData signUp(JSONObject jsonObject) {
        Role r = new Role();
        r.setUID(jsonObject.getString("userId"));
        r.setUserName(jsonObject.getString("userName"));
        r.setUserPassWord(passwordEncoder().encode(jsonObject.getString("password")));
        r.setUserRole(jsonObject.getString("userRole"));
        r.setUserInfo("A");
        roleMapper.registered_a(r);
        return null;
    }


    @Override
    public boolean logout(Role r) {
        return false;
    }


}
