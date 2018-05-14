package com.liaojun.component.base.common.model;

import com.liaojun.component.base.constant.ComponentBaseConstant;

/**
 * Created by ChamIt-001 on 2018/1/12.
 */
public class CheckFailedException extends BusinessException {

    public CheckFailedException(){
        setCode(ComponentBaseConstant.BASE_ERROR_CODE.PARAM_CHECK_ERROR);
    }

    public CheckFailedException(String message){
        super(message);
        setCode(ComponentBaseConstant.BASE_ERROR_CODE.PARAM_CHECK_ERROR);
    }
}
