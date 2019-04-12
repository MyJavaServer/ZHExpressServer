package com.zhihui.zhexpress.model;

import java.io.Serializable;
import java.util.List;

public class ExcelData implements Serializable {

    private static final long serialVersionUID = 4444017239100620999L;

    private List<String> titles;        // 表头
    private List<List<Object>> rows;    // 数据
    private String name;                // 页签名称


    public List<String> getTitles() {
        return titles;
    }

    public void setTitles(List<String> titles) {
        this.titles = titles;
    }

    public List<List<Object>> getRows() {
        return rows;
    }

    public void setRows(List<List<Object>> rows) {
        this.rows = rows;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
