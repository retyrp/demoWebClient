package com.example.demoWebClient.config.dao;

import org.apache.ibatis.jdbc.SQL;
import java.util.HashMap;

public class ConfigSqlProvider {

    public String updateConfigSql(HashMap<String,String> map){
        return new SQL(){
            {
                UPDATE("systemconfig");
                SET( "s_value = '"+map.values().iterator().next()+"'");
                WHERE("s_key = '"+ map.keySet().iterator().next() +"'");

            }
        }.toString();
    }

    public String insertConfigSql(HashMap<String,String> map){
        return new SQL(){
            {
                INSERT_INTO("systemconfig");
                map.forEach((key,value)->{
                    VALUES(key,"'"+value+"'");
                });
            }
        }.toString();
    }

    public String deleteConfigSql(HashMap<String,String> map){
        return new SQL(){
            {
                DELETE_FROM("systemconfig");
                map.forEach((key,value)->{
                    WHERE(key+" = '"+value+"'");
            });
            }
        }.toString();
    }

    public String selectConfigSql(HashMap<String,String> map){
        return new SQL(){
            {
                SELECT("*");
                FROM("SystemConfig");
                if(null != map) {
                    map.forEach((key, value) -> {
                        WHERE(key +"="+ "'"+value+"'");
                    });
                }
            }
        }.toString();
    }
}
