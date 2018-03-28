package com.example.demoWebClient.account.controller;


import com.sun.istack.internal.logging.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/login/account")
public class AccountController {

    Logger logger = Logger.getLogger(AccountController.class);

    @RequestMapping(value = "/in",method = RequestMethod.POST)
    public String login(){
        return "sucess";
    }

    @RequestMapping(value = "/getToken",method = RequestMethod.GET)
    public String getToken(){
        return "get it";
    }
}
