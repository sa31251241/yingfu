package com.liaojun.component.web.model;

import com.liaojun.component.base.db.model.PageRequest;
import com.liaojun.component.base.db.model.SortRequest;
import com.liaojun.component.base.util.StringUtil;

/**
 * Created by ChamIt-001 on 2017/10/24.
 */
public class ApiQueryRequest extends ApiRequest {

    private Integer page;
    private Integer limit;
    private String sortKey;
    private String sortDirection;

    public PageRequest getPageRequest(){
        if(this.page == null || this.limit == null){
            return null;
        }
        return new PageRequest(this.page,this.limit);
    }

    public SortRequest getSortRequest(){
        if(StringUtil.isEmpty(this.sortKey) || StringUtil.isEmpty(this.sortDirection)){
            return null;
        }
        return new SortRequest(this.sortKey,this.sortDirection);
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

    public String getSortKey() {
        return sortKey;
    }

    public void setSortKey(String sortKey) {
        this.sortKey = sortKey;
    }

    public String getSortDirection() {
        return sortDirection;
    }

    public void setSortDirection(String sortDirection) {
        this.sortDirection = sortDirection;
    }
}
