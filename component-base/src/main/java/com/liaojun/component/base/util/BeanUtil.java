package com.liaojun.component.base.util;

import com.liaojun.component.base.annotation.DictField;
import com.liaojun.component.base.common.model.BusinessException;
import com.liaojun.component.base.constant.ComponentBaseConstant;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.data.annotation.Transient;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
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

    public static <T> Map<String, Object> getBeanMap(T t) {
        if (t == null) {
            return null;
        }
        try {
            // 解析bean到map
            Map<String, Object> beanMap = PropertyUtils.describe(t);
            // 解析字段注解
            Field[] fields = t.getClass().getDeclaredFields();
            for (Field field : fields) {
                if (field.isAnnotationPresent(DictField.class)) {
                    DictField dictField = field.getAnnotation(DictField.class);
                    field.setAccessible(true);
                    if (!StringUtil.isEmpty(dictField.constantKey())) {
                        // 从常量获取字典
                        beanMap.put(StringUtil.concat(field.getName(), ComponentBaseConstant.DIC.FIELD_DIC_SUFFIX),
                                ComponentBaseConstant.valueDescMapCons.get(dictField.constantKey()).get(field.get(t)));
                    }
                }
            }
            return beanMap;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <T> List<Map<String, Object>> getBeanMapList(List<T> beanList) {
        List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
        for (Object obj : beanList) {
            resultList.add(getBeanMap(obj));
        }
        return resultList;
    }

    public static <T> Map<String, Object> describeBean(T t) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        if (t == null) {
            return null;
        }
        // 解析bean到map
        Map<String, Object> beanMap = PropertyUtils.describe(t);
        if(beanMap.containsKey("class") && beanMap.get("class") instanceof Class){
            beanMap.remove("class");
        }
        // 解析字段注解
        Field[] fields = t.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(DictField.class) && !(field.isAnnotationPresent(Transient.class))) {
                DictField dictField = field.getAnnotation(DictField.class);
                if (dictField.isRefField() && dictField.refType().equals(DictField.DICTFIELD_REFTYPE.BEAN)) {
                    field.setAccessible(true);
                    beanMap.put(field.getName(), describeBean(field.get(t)));
                } else if (dictField.isRefField() && dictField.refType().equals(DictField.DICTFIELD_REFTYPE.LIST)) {
                    field.setAccessible(true);
                    List<Map<String, Object>> fieldMapList = new ArrayList<Map<String, Object>>();
                    List tmpList = (List) field.get(t);
                    for (Object obj : tmpList) {
                        fieldMapList.add(describeBean(obj));
                    }
                    beanMap.put(field.getName(), fieldMapList);
                }
            }
        }
        return beanMap;
    }
}
