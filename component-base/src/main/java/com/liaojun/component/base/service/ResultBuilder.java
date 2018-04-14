package com.liaojun.component.base.service;

import com.liaojun.component.base.common.model.Result;
import com.liaojun.component.base.constant.ComponentBaseConstant;

/**
 * Created by ChamIt-001 on 2017/10/24.
 */
public class ResultBuilder {

    private static final String saveSuccessMessage = "保存完成";
    private static final String updateSuccessMessage = "更新完成";
    private static final String deleteSuccessMessage = "删除完成";
    private static final String noAuthMessage = "没有权限";
    private static final String noLoginMessage = "未登录";

    public static Result saveSuccessResult(){
        return new Result(true,saveSuccessMessage);
    }

    public static Result saveSuccessResult(Object data){
        return new Result(true,saveSuccessMessage).setData(data);
    }

    public static Result updateSuccessResult(){
        return new Result(true,updateSuccessMessage);
    }

    public static Result updateSuccessResult(Object data){
        return new Result(true,updateSuccessMessage).setData(data);
    }

    public static Result deleteSuccessResult(){
        return new Result(true,deleteSuccessMessage);
    }

    public static Result checkFailedResult(String message){
        return new Result(false,message).setCode(ComponentBaseConstant.BASE_ERROR_CODE.PARAM_CHECK_ERROR);
    }

    public static Result noAuthResult(){
        return new Result(false,noAuthMessage).setCode(ComponentBaseConstant.BASE_ERROR_CODE.AUTH_FORBIDDEN);
    }

    public static Result noLoginResult(){
        return new Result(false,noLoginMessage).setCode(ComponentBaseConstant.BASE_ERROR_CODE.AUTH_NOT_LOGIN);
    }

}
