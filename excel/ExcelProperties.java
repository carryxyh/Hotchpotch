package com.netease.music.musician.excel;

import org.apache.poi.ss.formula.functions.T;

import java.text.SimpleDateFormat;
import java.util.*;

public class ExcelProperties {

    /**
     * sheet名称和表头值
     */
    private  String sheetName="";
    /**
     * 表头信息（对象属性名称->要显示的标题值)[按顺序添加]
     */
    private Map<String, String> titleMap=new HashMap<>();
    /**
     * 时间行
     */
    private  String date=new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss").format(new Date());
    /**
     * 对象集合
     */
    private List<T> dataList;
    /**
     * 文件地址
     */
    private String fileUrl="./"+ UUID.randomUUID() + ".xls";


    public String getSheetName() {
        return sheetName;
    }

    public void setSheetName(String sheetName) {
        this.sheetName = sheetName;
    }

    public Map<String, String> getTitleMap() {
        return titleMap;
    }

    public void setTitleMap(Map<String, String> titleMap) {
        this.titleMap = titleMap;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<T> getDataList() {
        return dataList;
    }

    public void setDataList(List dataList) {
        this.dataList = dataList;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }
}
