package com.liaojun.component.base.db.model;

/**
 * Created by ChamIt-001 on 2017/10/10.
 */
public class PageRequest {

    private Integer page;
    private Integer limit;

    public PageRequest(Integer page,Integer limit){
        setPage(page);
        setLimit(limit);
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }
}
