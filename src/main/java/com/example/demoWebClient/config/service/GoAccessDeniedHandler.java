package com.example.demoWebClient.config.service;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GoAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException {
        httpServletResponse.setHeader("Content-Type","application/json;charset=utf-8");
        httpServletResponse.getWriter().print("{\"code\":1,\"message\":\""+e.getMessage()+"\"}");
        httpServletResponse.getWriter().flush();
    }
}
