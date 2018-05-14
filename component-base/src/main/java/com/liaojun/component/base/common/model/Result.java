package com.liaojun.component.base.common.model;

import com.alibaba.fastjson.JSONObject;
import com.liaojun.component.base.constant.ComponentBaseConstant;

/**
 * Created by ChamIt-001 on 2017/10/10.
 */
public class Result {

    public static final Result EMPTY_SUCC_RESULT = new Result(true);

    private Boolean success;
    private String message;
    private String code;
    private Object data;

    public Result() {};

    public Result(Boolean success) {
        this.setSuccess(success);
    }

    public Result(Boolean success,String message) {
        this.setSuccess(success);
        this.setMessage(message);
    }

    public Result(Boolean success,String message,String code) {
        this.setSuccess(success);
        this.setMessage(message);
        this.setCode(code);
    }

    public Boolean isSuccess() {
        if(success == true){
            setCode(ComponentBaseConstant.BASE_ERROR_CODE.SUCCESS);
        } else {
            setCode(ComponentBaseConstant.BASE_ERROR_CODE.PARAM_CHECK_ERROR);
        }
        return success;
    }

    public Result setSuccess(Boolean success) {
        this.success = success;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public Result setMessage(String message) {
        this.message = message;
        return this;
    }

    public String getCode() {
        return code;
    }

    public Result setCode(String code) {
        this.code = code;
        return this;
    }

    public Object getData() {
        return data;
    }

    public Result setData(Object data) {
        this.data = data;
        return this;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
