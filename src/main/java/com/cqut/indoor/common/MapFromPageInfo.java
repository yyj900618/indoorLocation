package com.cqut.indoor.common;


import com.github.pagehelper.PageInfo;

import java.io.Serializable;
import java.util.List;


public class MapFromPageInfo<T> implements Serializable {
    private int pages;
    private long total;
    private int startRow;
    private int endRow;
    private List<T> list;

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public int getStartRow() {
        return startRow;
    }

    public void setStartRow(int startRow) {
        this.startRow = startRow;
    }

    public int getEndRow() {
        return endRow;
    }

    public void setEndRow(int endRow) {
        this.endRow = endRow;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public MapFromPageInfo(){}
    public MapFromPageInfo(List<T> list){
        PageInfo pageInfo = new PageInfo(list);
        this.pages=pageInfo.getPages();
        this.total=pageInfo.getTotal();
        this.startRow= pageInfo.getStartRow();
        this.endRow=pageInfo.getEndRow();
        this.list=list;
    }
    public MapFromPageInfo(List<T> list, List<T> list02){
        PageInfo pageInfo = new PageInfo(list);
        this.pages=pageInfo.getPages();
        this.total=pageInfo.getTotal();
        this.startRow= pageInfo.getStartRow();
        this.endRow=pageInfo.getEndRow();
        this.list=list02;
    }
}
