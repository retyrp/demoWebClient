package com.example.demoWebClient.config.service;

import com.example.demoWebClient.foundation.cache.CacheManager;
import com.example.demoWebClient.foundation.dto.ResultData;
import com.example.demoWebClient.foundation.service.RSAFactory;
import com.example.demoWebClient.foundation.service.TimeFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GoAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        httpServletResponse.setHeader("Content-Type","application/json;charset=utf-8");
        ResultData data = new ResultData();
        List<Map> list = new ArrayList<>();
        Map map = new HashMap();
        map.put("key","value");
        map.put("yourUri",httpServletRequest.getRequestURI());
        map.put("RemoteAddr",httpServletRequest.getRemoteAddr());
        map.put("RemoteHost",httpServletRequest.getRemoteHost());
        map.put("RemotePort",httpServletRequest.getRemotePort());

        map.put("RemoteUser",httpServletRequest.getRemoteUser());

        map.put("ParameterMap",httpServletRequest.getParameterMap().get("_csrf_header")[0]);
        list.add(map);
        //String testData = httpServletRequest.getSession().getAttribute("_csrf").toString();
        //map.put("Data",testData);

        list.add(httpServletRequest.getParameterMap());
        data.setSerialNo("");
        data.setSign("Error_"+ CacheManager.getContent("user_login"));
        data.setTimeStamp(TimeFactory.getTimeStampNow());
        data.setData(list);
        RSAFactory.encryptByPrivateKey(,RSAFactory.getPrivateKey())
        httpServletResponse.getWriter().print(data.toJSONObject());
        httpServletResponse.getWriter().flush();

    }
}
