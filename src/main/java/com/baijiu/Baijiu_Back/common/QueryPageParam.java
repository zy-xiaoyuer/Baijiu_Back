package com.baijiu.Baijiu_Back.common;

import lombok.Data;

import java.util.HashMap;

@Data
public class QueryPageParam {
    //默认
    private static int PAGE_SIZE=4;
    private static int Page_NUM=1;

    private int pageSize=PAGE_SIZE;
    private int pageNum=Page_NUM;

    private HashMap params=new HashMap();

    public HashMap getParam() {
        return params;
    }

    public void setParam(HashMap params) {
        this.params = params;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }
}
