package com.example.demoWebClient.config.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SystemConfig {

    private Map systemName;
    private Map systemUpdate;
    private Map systemAdmin;

    public Map getSystemName() {
        return systemName;
    }

    public void setSystemName(Map systemName) {
        this.systemName = systemName;
    }

    public Map getSystemUpdate() {
        return systemUpdate;
    }

    public void setSystemUpdate(Map systemUpdate) {
        this.systemUpdate = systemUpdate;
    }

    public Map getSystemAdmin() {
        return systemAdmin;
    }

    public void setSystemAdmin(Map systemAdmin) {
        this.systemAdmin = systemAdmin;
    }

    public List<String> getAllCols(){
        List<String> resultList = new ArrayList<>();

        resultList.add("systemName");
        resultList.add("systemUpdate");

        return resultList;
    }
}
