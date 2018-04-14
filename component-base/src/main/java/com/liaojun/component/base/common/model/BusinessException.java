package com.liaojun.component.base.common.model;

/**
 * Created by ChamIt-001 on 2017/10/22.
 */
public class BusinessException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private String code;

    public BusinessException(){}

    public String getCode() {
        return code;
    }

    public BusinessException setCode(String code) {
        this.code = code;
        return this;
    }

    public BusinessException(String message){
        super(message);
    }

    public BusinessException(Throwable cause)
    {
        super(cause);
    }

    public BusinessException(String message,Throwable cause)
    {
        super(message,cause);
    }

    public BusinessException(String code,String message){
        super(message);
        setCode(code);
    }
}
