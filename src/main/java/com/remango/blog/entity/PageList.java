package com.remango.blog.entity;

import java.util.List;

/**
 * Created by lenovo on 2019/3/8.
 */
public class PageList<T> {
    private int total;
    private List<T> rows;

    public PageList() {
    }

    public PageList(int total, List<T> rows) {
        this.total = total;
        this.rows = rows;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }
}
