package com.liaojun.component.base.util;

import com.liaojun.component.base.common.model.BusinessException;
import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * Created by ChamIt-001 on 2018/1/18.
 */
public class BeanUtil extends BeanUtils {

    public static <T> T populateBean(T target,Map<String,? extends Object> properties) {
        try {
            populate(target,properties);
        } catch (IllegalAccessException e) {
            throw new BusinessException(e);
        } catch (InvocationTargetException e) {
            throw new BusinessException(e);
        }
        return target;
    }
}
