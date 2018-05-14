package com.liaojun.component.base.service;

import com.liaojun.component.base.common.model.BusinessException;
import com.liaojun.component.base.common.model.CheckFailedException;
import com.liaojun.component.base.common.model.Result;

/**
 * Created by ChamIt-001 on 2017/10/22.
 */
public class ExceptionFactory {
    public static BusinessException createCheckError(String message){
        return new CheckFailedException(message);
    }

    public static BusinessException create(Result result){
        return new BusinessException(result.getCode(),result.getMessage());
    }

    public static BusinessException create(String code,String message){
        return new BusinessException(code,message);
    }
}
