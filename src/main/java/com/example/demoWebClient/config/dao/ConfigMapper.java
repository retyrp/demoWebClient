package com.example.demoWebClient.config.dao;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import java.util.List;
import java.util.Map;

@Mapper
public interface ConfigMapper {

    @UpdateProvider(type=ConfigSqlProvider.class,method = "updateConfigSql")
    int updateConfig(Map map);

    @SelectProvider(type=ConfigSqlProvider.class,method = "selectConfigSql")
    List<Map> loadConfig(Map map);

    @InsertProvider(type=ConfigSqlProvider.class,method = "insertConfigSql")
    int insertConfig(Map map);

}
