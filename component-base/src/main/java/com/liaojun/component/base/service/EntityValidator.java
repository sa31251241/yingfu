package com.liaojun.component.base.service;

import com.liaojun.component.base.common.model.ValidateItem;
import com.liaojun.component.base.util.StringUtil;

import java.util.List;
import java.util.Map;

/**
 * Created by ChamIt-001 on 2018/1/15.
 */
public class EntityValidator {

    private Map<String,List<ValidateItem>> itemMap;

    public void validate(String itemKey,Object checkObj,String operationType){
        if(itemMap.containsKey(itemKey) && itemMap.get(itemKey) != null){
            for(ValidateItem item:itemMap.get(itemKey)){
                if(StringUtil.isEmpty(item.getOperationType()) || item.getOperationType().equals(operationType)){
                    if(item.getCheckType().equals(Validator.CHECK_TYPE.EMPTY.getValue())){
                    }
                }
            }
        }
    }
}
