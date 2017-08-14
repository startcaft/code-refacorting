package com.basic.core.entity.vo;

import java.util.List;

/**
 * jgGrid 接受的 JSON 格式
 * @param <T>
 */
public class GridVo<T> {

    private int total;          //总页数
    private int page;           //当前请求页
    private int records;        //总记录数
    private List<T> rows;       //具体的数据

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getRecords() {
        return records;
    }

    public void setRecords(int records) {
        this.records = records;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }
}
