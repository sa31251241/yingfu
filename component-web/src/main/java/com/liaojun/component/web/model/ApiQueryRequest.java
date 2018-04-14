package com.liaojun.component.web.model;

import com.liaojun.component.base.db.model.PageRequest;
import com.liaojun.component.base.db.model.SortRequest;
import com.liaojun.component.base.util.StringUtil;

/**
 * Created by ChamIt-001 on 2017/10/24.
 */
public class ApiQueryRequest extends ApiRequest {

    private Integer pageIndex;
    private Integer pageSize;
    private String sortKey;
    private String sortDirection;

    public PageRequest getPageRequest(){
        if(this.pageIndex == null || this.pageSize == null){
            return null;
        }
        return new PageRequest(this.pageIndex,this.pageSize);
    }

    public SortRequest getSortRequest(){
        if(StringUtil.isEmpty(this.sortKey) || StringUtil.isEmpty(this.sortDirection)){
            return null;
        }
        return new SortRequest(this.sortKey,this.sortDirection);
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
