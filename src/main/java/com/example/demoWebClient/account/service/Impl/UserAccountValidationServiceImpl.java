package com.example.demoWebClient.account.service.Impl;

import com.example.demoWebClient.account.dao.RoleMapper;
import com.example.demoWebClient.account.dao.UacMapper;
import com.example.demoWebClient.account.dto.Role;
import com.example.demoWebClient.account.dto.UAC;
import com.example.demoWebClient.account.service.UserAccountValidationService;
import org.apache.log4j.Logger;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Date;


@Service
public class UserAccountValidationServiceImpl implements UserAccountValidationService{

    Logger logger = Logger.getLogger(UserAccountValidationServiceImpl.class);

    @Autowired
    private  RoleMapper roleMapper;

    @Autowired
    private UacMapper uacMapper;

    @Autowired
    private AmqpTemplate rabbitTemplate;



    @Override
    public boolean isAdmission(Role r) {

        /** 查询权限 */
        UAC uac = new UAC();
        uac.setRoleID(r.getUID());
        try {
            uac = uacMapper.getAuthInfo(uac).get(0);
            uac = this.uacMapper.getAuthInfo(uac).get(0);
        }catch (Exception e){
            e.printStackTrace();
            logger.info("--[获取权限失败][用户:"+r.getUID()+"]");
        }

        return false;
    }

    @Override
    public boolean login(Role r) {
        String context = "hello"+ new Date();
        System.out.println(context);
        rabbitTemplate.convertAndSend("hello",context);
        return false;
    }

    @Override
    public boolean logout(Role r) {
        return false;
    }

    @Override
    public Role getUserByUserId(String userId) {
        Role r = new Role();
        try {
            r.setUID(userId);
            r = roleMapper.login(r);
        }catch (Exception e){
            e.printStackTrace();
            logger.warn("[userId]:"+userId+"(ToGet)[Role]:"+r.toString());
        }
        return r;
    }
}
