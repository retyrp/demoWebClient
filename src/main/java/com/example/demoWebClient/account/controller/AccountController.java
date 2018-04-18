package com.example.demoWebClient.account.controller;


import com.alibaba.fastjson.JSONObject;
import com.example.demoWebClient.account.service.AccountService;
import com.example.demoWebClient.foundation.dto.ResultData;
import com.example.demoWebClient.foundation.service.RSAFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import sun.misc.BASE64Decoder;

@RestController
@RequestMapping(value = "/login/account")
public class AccountController {

    //Logger logger = Logger.getLogger(AccountController.class);

    @Autowired
    AccountService accountServiceImpl;
    /*@RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login(@AuthenticationPrincipal @RequestBody String jsonObjectString){
        JSONObject jsonObject = JSONObject.parseObject(jsonObjectString);
        return "success";
    }*/

    @RequestMapping(value = "register",method = RequestMethod.POST)
    public ResultData register(@RequestBody String jsonObjectString){
        JSONObject jsonObject = JSONObject.parseObject(jsonObjectString);
        accountServiceImpl.signUp(jsonObject);
        return null;
    }

    @RequestMapping(value = "/getInfo",method = RequestMethod.POST)
    public String getInfo(@RequestBody String jsonObjectString){
        System.err.println(jsonObjectString);
        return jsonObjectString;
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
