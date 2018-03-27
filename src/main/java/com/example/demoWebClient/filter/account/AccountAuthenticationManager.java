package com.example.demoWebClient.filter.account;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demoWebClient.account.dto.Role;
import org.apache.commons.lang.StringUtils;
import org.jsoup.Jsoup;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;

public class AccountAuthenticationManager implements AuthenticationManager{

    private static final List<GrantedAuthority> AUTHORITIES = new ArrayList<>();

    private static final String userInfoUri = "";

    private static final String USER_INFO_API = "";

    private static final String clientId = "";

    static {
        AUTHORITIES.add(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if(StringUtils.isNotBlank(authentication.getName()) && null != authentication.getCredentials()){
            Role role = getRoleInfo(authentication.getName(),authentication.getCredentials().toString());
            return new UsernamePasswordAuthenticationToken(role,null, AUTHORITIES);
        }else{
            throw new BadCredentialsException("Bad Credentials");
        }
    }

    private Role getRoleInfo(String accessToken, String openId){
        String url = String.format(USER_INFO_API, userInfoUri, accessToken, clientId, openId);
        JSONObject jsonObject;
        try{
            jsonObject= JSON.parseObject(Jsoup.connect(url).post().text());
        }catch (Exception e){
            throw new BadCredentialsException("Bad Credentials!");
        }
        Role role = new Role();
        role.setUID(jsonObject.getString("UID"));
        role.setUserInfo(jsonObject.getString("userInfo"));
        role.setUserName(jsonObject.getString("userName"));
        role.setUserPassWord(jsonObject.getString("userPassWord"));
        role.setUserRole(jsonObject.getString("userRole"));

        return role;
    }
}
