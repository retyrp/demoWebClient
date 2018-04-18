package com.example.demoWebClient.foundation.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExcelHelper {

    public static JSONObject readExcel(String fileName) {
        JSONObject jsonResult = new JSONObject();
        List<Map> list = new ArrayList<>();
        Map map = new HashMap<String, String>();
        try {
            String excelPath = fileName;//CacheManager.getContent("excelPath").getValue().toString();
            File excel = new File(excelPath);
            if (excel.isFile() && excel.exists()) {
                String[] split = excel.getName().split("\\.");  //.是特殊字符，需要转义！！！！！
                Workbook wb;
                //根据文件后缀（xls/xlsx）进行判断
                if ("xls".equals(split[1])) {
                    FileInputStream fis = new FileInputStream(excel);   //文件流对象
                    wb = new HSSFWorkbook(fis);
                } else if ("xlsx".equals(split[1])) {
                    wb = new XSSFWorkbook(excel);
                } else {
                    System.out.println("文件类型错误!");
                    return null;
                }

                //开始解析
                Sheet sheet = wb.getSheetAt(0);     //读取sheet 0

                int firstRowIndex = sheet.getFirstRowNum() + 1;   //第一行是列名，所以不读
                int lastRowIndex = sheet.getLastRowNum();

                List titles = new ArrayList<String>();
                sheet.getRow(0).forEach(s -> {
                    titles.add(s);
                });
                StringBuilder sb = new StringBuilder();
                sb.append("{\"data\":[");
                sheet.forEach(row -> {
                    sb.append("{");
                    row.forEach(cell -> {
                        sb.append("\"")
                                .append(titles.get(cell.getColumnIndex()))
                                .append("\":\"")
                                .append(cell)
                                .append("\",");
                    });
                    sb.delete(sb.length() - 1, sb.length());
                    sb.append("},");
                });
                sb.delete(sb.length() - 1, sb.length());
                sb.append("]}");
                jsonResult = JSONObject.parseObject(sb.toString());
                /*for (int rIndex = firstRowIndex; rIndex <= lastRowIndex; rIndex++) {   //遍历行
                    System.out.println("rIndex: " + rIndex);
                    Row row = sheet.getRow(rIndex);
                    if (row != null) {
                        int firstCellIndex = row.getFirstCellNum();
                        int lastCellIndex = row.getLastCellNum();
                        for (int cIndex = firstCellIndex; cIndex < lastCellIndex; cIndex++) {   //遍历列
                            Cell cell = row.getCell(cIndex);
                            if (cell != null) {
                                map.put("userId",cell.toString());
                            }
                        }
                    }
                }*/
            } else {
                System.out.println("找不到指定的文件");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonResult;
    }
}
