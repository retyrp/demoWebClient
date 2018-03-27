package com.example.demoWebClient.token;

/**
 * @author YRP
 * @Title: Token
 * @date 2018/3/16 11:46
 */
public class Token {

    /**
     * token
     */
    private String accessToken;

    /**
     * 有效期
     */
    private int expiresIn;

    /**
     * 刷新token
     */
    private String refresh_token;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public int getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(int expiresIn) {
        this.expiresIn = expiresIn;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }
}
