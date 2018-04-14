package com.liaojun.component.web.model;

import com.liaojun.component.base.common.model.Result;
import com.liaojun.component.base.constant.ComponentBaseConstant;

/**
 * Created by ChamIt-001 on 2017/10/24.
 */
public class ApiResult extends Result {

    private String token;

    public ApiResult(){}

    public ApiResult(Boolean success) {
        super(success);
    }

    public ApiResult(Boolean success, String message) {
        super(success, message);
    }

    public ApiResult(Boolean success, String message, String code) {
        super(success, message, code);
    }

    public ApiResult(Result result){
        setSuccess(result.isSuccess());
        setMessage(result.getMessage());
        setCode(result.getCode());
        setData(result.getData());
    }

    @Override
    public ApiResult setSuccess(Boolean success) {
        super.setSuccess(success);
        return this;
    }

    @Override
    public ApiResult setMessage(String message) {
        super.setMessage(message);
        return this;
    }

    @Override
    public ApiResult setCode(String code) {
        super.setCode(code);
        return this;
    }

    @Override
    public String getCode() {
        if(super.getCode() == null){
            return this.isSuccess() ? ComponentBaseConstant.BASE_ERROR_CODE.SUCCESS : ComponentBaseConstant.BASE_ERROR_CODE.PARAM_CHECK_ERROR;
        } else {
            return super.getCode();
        }
    }

    @Override
    public ApiResult setData(Object data) {
        super.setData(data);
        return this;
    }

    @Override
    public Object getData() {
        return super.getData() == null ? new Object() : super.getData();
    }

    public String getToken() {
        return token;
    }

    public ApiResult setToken(String token) {
        this.token = token;
        return this;
    }


}
