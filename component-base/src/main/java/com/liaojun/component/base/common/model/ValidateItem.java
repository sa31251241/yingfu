package com.liaojun.component.base.common.model;

/**
 * Created by ChamIt-001 on 2018/1/15.
 */
public class ValidateItem {

    private String key;
    private String operationType;
    private String checkType;
    private String errmsg;

    public ValidateItem(String key,String operationType,String checkType,String errmsg){
        this.key = key;
        this.operationType = operationType;
        this.checkType = checkType;
        this.errmsg = errmsg;
    }

    public String getKey() {
        return key;
    }

    public String getOperationType() {
        return operationType;
    }

    public String getCheckType() {
        return checkType;
    }

    public String getErrmsg() {
        return errmsg;
    }
}
