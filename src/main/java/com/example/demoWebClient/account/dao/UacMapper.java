package com.example.demoWebClient.account.dao;

import com.example.demoWebClient.account.dto.UAC;

import java.util.List;

/**
 * @author YRP
 * @Title: UacMapper
 * @date 2018/3/7 15:42
 */
public interface UacMapper {

    List<UAC> getAuthInfo(UAC uac);

    void updateAuthInfo(UAC uac);

    void addRecord(UAC uac);

    void deleRecord(UAC uac);
}
