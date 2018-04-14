package com.liaojun.component.web.converter;

import com.alibaba.fastjson.JSONObject;
import com.liaojun.component.base.common.model.BusinessException;
import com.liaojun.component.base.constant.ComponentBaseConstant;
import com.liaojun.component.web.model.ApiRequest;
import org.springframework.stereotype.Component;

/**
 * Created by ChamIt-001 on 2017/10/30.
 */
@Component
public class ApiRequestToBeanConverter {

    public <T> T convert(ApiRequest apiRequest,Class cls) {
        try {
            T obj = (T) JSONObject.parseObject(JSONObject.toJSONString(apiRequest.getParams()),cls);
            return obj;
        } catch (Exception e){
            throw new BusinessException("转换异常:" + JSONObject.toJSONString(apiRequest.getParams())).setCode(ComponentBaseConstant.BASE_ERROR_CODE.CONVERT_ERROR);
        }
    }
}
