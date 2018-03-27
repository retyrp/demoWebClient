package com.example.demoWebClient.filter.account;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demoWebClient.token.Token;
import org.apache.commons.lang.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AccountAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    private final static String CODE = "code";

    /** 获取 Token 的 API */
    private final static String accessTokenUri = "";

    /** grant_type */
    private final static String grantType = "authorization_code";

    /** client_id */
    private final String clientId = "101386962";

    /** client_secret */
    private final static String clientSecret = "";

    /** redirect_uri 回调地址*/
    private final static String redirectUri = "";

    /** 获取 OpenID 的 API 地址*/
    private final static String openIdUri = "";

    /** 获取 token 的地址拼接 */
    private final static String TOKEN_ACCESS_API = "";

    public AccountAuthenticationFilter(String defaultFilterProcessesUrl) {
        super(new AntPathRequestMatcher(defaultFilterProcessesUrl,"POST"));
    }

    public AccountAuthenticationFilter(RequestMatcher requiresAuthenticationRequestMatcher) {
        super(requiresAuthenticationRequestMatcher);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws AuthenticationException, IOException, ServletException {
        String code = httpServletRequest.getParameter(CODE);
        System.out.println("Code : "+ code);
        String tokenAccessApi = String.format(TOKEN_ACCESS_API,accessTokenUri,grantType,clientId,clientSecret,code,redirectUri);
        Token token = this.getToken(tokenAccessApi);
        System.out.println(JSON.toJSONString(token));
        if(null != token){
            String openId = getOpenId(token.getAccessToken());
            System.out.println(openId);
            if(StringUtils.isNotBlank(openId)){
                UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(token.getAccessToken(),openId);
                return this.getAuthenticationManager().authenticate(authRequest);
            }
        }
        return null;
    }


    /**
     * 获取Token
     * @param tokenAccessApi
     * @return
     * @throws IOException
     */
    private Token getToken(String tokenAccessApi) throws  IOException{
        JSONObject jsonObject = JSON.parseObject(Jsoup.connect(tokenAccessApi).get().text());
        if(!jsonObject.isEmpty()){
            Token token = new Token();
            String accessToken = jsonObject.getString("access_token");
            String refreshToken = jsonObject.getString("refresh_token");
            int expiresIn = jsonObject.getInteger("expires_in");
            token.setAccessToken(accessToken);
            token.setExpiresIn(expiresIn);
            token.setRefresh_token(refreshToken);
            return token;
        }
        return null;
    }

    /**
     * 获取openId
     * @param accessToken
     * @return
     * @throws IOException
     */
    private String getOpenId(String accessToken) throws IOException{
        String url = openIdUri + accessToken;
        JSONObject jsonObject = JSON.parseObject(Jsoup.connect(url).get().text());
        return jsonObject.getString("openId");
    }
}
