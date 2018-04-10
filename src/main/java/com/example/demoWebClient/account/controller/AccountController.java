package com.example.demoWebClient.account.controller;


import com.alibaba.fastjson.JSONObject;
import com.example.demoWebClient.foundation.cache.CacheManager;
import com.example.demoWebClient.foundation.service.RSAFactory;
import com.sun.istack.internal.logging.Logger;
import org.bouncycastle.util.encoders.Base64Encoder;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import sun.misc.BASE64Decoder;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/login/account")
public class AccountController {

    Logger logger = Logger.getLogger(AccountController.class);

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login(@AuthenticationPrincipal @RequestBody String jsonObjectString){
        JSONObject jsonObject = JSONObject.parseObject(jsonObjectString);
        return "success";
    }

    @RequestMapping(value = "/getToken",method = RequestMethod.GET)
    public String getToken(){
        return "get it";
    }

    @RequestMapping(value = "/verify",method = RequestMethod.POST)
    public String verify(@RequestBody String jsonObjectString) throws Exception {
        JSONObject jsonObject = JSONObject.parseObject(jsonObjectString);
        String sign = jsonObject.getString("sign");
        String lm = jsonObject.getString("data");
        System.out.println("--------"+sign);
        System.out.println("--------"+lm);
        if(RSAFactory.verify(new BASE64Decoder().decodeBuffer(lm),new BASE64Decoder().decodeBuffer(sign)))
            return "success!";
        return "failure";
    }
}
