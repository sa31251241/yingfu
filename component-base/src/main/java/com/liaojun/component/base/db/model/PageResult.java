package com.liaojun.component.base.db.model;

import java.util.List;

/**
 * Created by ChamIt-001 on 2017/10/10.
 */
public class PageResult {

    private Integer pageIndex;
    private Integer pageSize;
    private Integer recordCount;
    private Integer pageCount;
    private List records;

    public PageResult(PageRequest pageRequest,Integer recordCount,List records){
        setPageIndex(pageRequest.getPageIndex());
        setPageSize(pageRequest.getPageSize());
        setRecordCount(recordCount);
        setRecords(records);
        calculatePageCount();
    }

    private void calculatePageCount(){
        int divisor = this.getRecordCount() / this.getPageSize();
        int remainder = this.getRecordCount() % this.getPageSize();
        this.setPageCount(remainder == 0 ? divisor == 0 ? 1 : divisor : divisor + 1);
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

    public Integer getRecordCount() {
        return recordCount;
    }

    public void setRecordCount(Integer recordCount) {
        this.recordCount = recordCount;
    }

    public Integer getPageCount() {
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    public List getRecords() {
        return records;
    }

    public void setRecords(List records) {
        this.records = records;
    }
}
