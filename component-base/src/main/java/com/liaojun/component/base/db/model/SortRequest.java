package com.liaojun.component.base.db.model;

import com.liaojun.component.base.constant.ComponentBaseConstant;

/**
 * Created by ChamIt-001 on 2017/10/10.
 */
public class SortRequest {

    private String sortKey;
    private String sortDirection;

    public SortRequest(String sortKey,String sortDirection){
        setSortKey(sortKey);
        setSortDirection(sortDirection.toLowerCase());
    }

    public static SortRequest ascRequest(String sortKey){
        return new SortRequest(sortKey, ComponentBaseConstant.SORT_REQUEST_DIRECT.ASC.getValue());
    }

    public static SortRequest descRequest(String sortKey){
        return new SortRequest(sortKey, ComponentBaseConstant.SORT_REQUEST_DIRECT.DESC.getValue());
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
