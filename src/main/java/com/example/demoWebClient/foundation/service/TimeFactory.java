package com.example.demoWebClient.foundation.service;

import org.springframework.stereotype.Service;

import java.util.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Pattern;

/**
 * @author YRP
 * @Title: TimeFactory 时间服务
 * @date 2018/3/29 15:08
 */
public class TimeFactory {

    /**
     * 取得当前时间-SQL格式
     * 2018-03-30 14:18:43.241
     * @return
     */
    public static java.sql.Timestamp getSqlTime() {
        return new java.sql.Timestamp(new Date().getTime());
    }

    /**
     * 获取当前时间-时间戳
     * 1522390936800
     * @return
     */
    public static String getTimeStampNow(){
        return String.valueOf(new Date().getTime());
    }

    /**
     * 时间转化（String ===> sql）
     *
     * @param s
     * @return
     * @throws ParseException
     */
    public static java.sql.Timestamp getSqlTime(String s) {
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date d = (Date) formatter.parse(s);
            return new java.sql.Timestamp(d.getTime());
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取当前时间-String纯数字格式
     *
     * @return
     */
    public static String getStringTimeNumber() {
        String reg = "[^0-9]";
        Pattern p = Pattern.compile(reg);
        return (p.matcher((new java.sql.Timestamp((new Date().getTime()))).toString())).replaceAll("").trim();

    }

    /**
     * 获取当前时间-标准格式 2000-01-01 09:00:00
     *
     * @return
     */
    public static String getStringTimeStandard() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }

    /**
     * 返回当前日期与相差日期的天数-Timestamp （不满一日按一日算）
     *
     * @param t
     * @return
     */
    public static int getComplieDays(Timestamp t) {
        long temp = new Timestamp(new Date().getTime()).getTime() - t.getTime();
        return new Timestamp(temp > 0 ? temp : -temp).getDate();
    }

    /**
     * 放回当前日期与相差日期天数-Date （不满一日按一日算）
     *
     * @param d
     * @return
     */
    public static int getComplieDays(Date d) {
        long temp = new Date().getTime() - d.getTime();
        return new Date(temp > 0 ? temp : -temp).getDate();
    }

    /**
     * 检查是否标准日期格式 yyyy-MM-dd
     */
    public static boolean checkStand(String str) {
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = (Date) formatter.parse(str);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
