package com.example.demoWebClient.filter.account;

import com.alibaba.fastjson.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        if(null != request.getContentType() && (request.getContentType().equals(MediaType.APPLICATION_JSON_UTF8_VALUE)||request.getContentType().equals(MediaType.APPLICATION_JSON_VALUE))) {
            UsernamePasswordAuthenticationToken authRequest = null;
            StringBuilder sb = new StringBuilder();
            String input;
            try {
                BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF-8"));
                while (null != (input = br.readLine())) sb.append(input);
                JSONObject jsonObject = JSONObject.parseObject(sb.toString());
                authRequest = new UsernamePasswordAuthenticationToken(
                        jsonObject.getString("user"), jsonObject.getString("pwd"));

            } catch (IOException e) {
                e.printStackTrace();
                authRequest = new UsernamePasswordAuthenticationToken(
                        "", "");
            }
            setDetails(request, authRequest);
            return this.getAuthenticationManager().authenticate(authRequest);
        } else {
            System.out.println(request.getParameterMap().values().toString());
            return super.attemptAuthentication(request, response);
        }
    }
}
