package com.liaojun.component.base.db.model;

/**
 * Created by ChamIt-001 on 2017/10/10.
 */
public class PageRequest {

    private Integer pageIndex;
    private Integer pageSize;

    public PageRequest(Integer pageIndex,Integer pageSize){
        setPageIndex(pageIndex);
        setPageSize(pageSize);
    }

    public Integer getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
