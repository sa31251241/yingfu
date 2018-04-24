package com.liaojun.component.mybatis.service.impl;

import com.liaojun.component.base.util.StringUtil;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: yangzi
 * @Date: 2018/4/10 14:27
 */
public class CriteriaUtil {

    public enum DB_OPERATOR_MATCHES {
        OR_SEPARATOR("||"),
        OR("__or"),
        IN("__in"),
        NIN("__nin"),
        GT("__gt"),
        GTE("__gte"),
        LT("__lt"),
        LTE("__lte"),
        BLK("__blk"),
        RLK("__rlk"),
        LLK("__llk"),
        BET("__bet"),
        NE("__ne"),
        IGNORE("__ign");
        private String value;
        DB_OPERATOR_MATCHES(String value){this.value = value;}
        public String getValue(){return this.value;}
    }

    public static Condition combineParam(Map<String,Object> paramMap,Class clazz) {
        Condition condition = new Condition(clazz);
        if(paramMap == null || paramMap.size() == 0){
            return condition;
        }
        Example.Criteria criteria = condition.createCriteria();
        for(Map.Entry<String,Object> entry: paramMap.entrySet()) {
            if(entry.getKey().contains(DB_OPERATOR_MATCHES.OR.getValue())) {
                //或
                String fieldName = entry.getKey().substring(0,entry.getKey().indexOf(DB_OPERATOR_MATCHES.OR.getValue()));
                List<Object> orList = (List<Object>)entry.getValue();
                for(Object orValue : orList) {
                    criteria.orEqualTo(fieldName,orValue);
                }
            }else if(entry.getKey().contains(DB_OPERATOR_MATCHES.IN.getValue())) {
                //包括
                String fieldName = entry.getKey().substring(0,entry.getKey().indexOf(DB_OPERATOR_MATCHES.IN.getValue()));
                List<Object> rangeList = (List<Object>) entry.getValue();
                criteria.andIn(fieldName,rangeList);
            }else if(entry.getKey().contains(DB_OPERATOR_MATCHES.NIN.getValue())) {
                //不包括
                String fieldName = entry.getKey().substring(0,entry.getKey().indexOf(DB_OPERATOR_MATCHES.NIN.getValue()));
                List<Object> rangeList = (List<Object>) entry.getValue();
                criteria.andNotIn(fieldName,rangeList);
            } else if(entry.getKey().contains(DB_OPERATOR_MATCHES.OR_SEPARATOR.getValue())) {
                String[] querys = entry.getKey().split("\\|\\|");
                for(String query : querys) {
                    Map<String,Object> subQueryMap = new HashMap<String,Object>();
                    subQueryMap.put(query,entry.getValue());
                    Condition subCondition = combineParam(subQueryMap,clazz);
                    for (Example.Criteria subCriteria : subCondition.getOredCriteria()) {
                        condition.or(subCriteria);
                    }
                }
            } else if(entry.getKey().contains(DB_OPERATOR_MATCHES.GTE.getValue())) {
                //大于等于
                String fieldName = entry.getKey().substring(0,entry.getKey().indexOf(DB_OPERATOR_MATCHES.GTE.getValue()));
                if(paramMap.containsKey(StringUtil.concat(fieldName,DB_OPERATOR_MATCHES.LTE.getValue()))){
                    //当存在同字段小于等于时组合
                    criteria.andGreaterThanOrEqualTo(fieldName,entry.getValue()).andLessThanOrEqualTo(fieldName,paramMap.get(StringUtil.concat(fieldName,DB_OPERATOR_MATCHES.LTE.getValue())));
                } else {
                    criteria.andGreaterThanOrEqualTo(fieldName,entry.getValue());
                }
            } else if(entry.getKey().contains(DB_OPERATOR_MATCHES.LTE.getValue())) {
                //小于等于
                String fieldName = entry.getKey().substring(0,entry.getKey().indexOf(DB_OPERATOR_MATCHES.LTE.getValue()));
                if(paramMap.containsKey(StringUtil.concat(fieldName,DB_OPERATOR_MATCHES.GTE.getValue()))){
                    continue;
                }
                criteria.andLessThanOrEqualTo(fieldName,entry.getValue());
            } else if(entry.getKey().contains(DB_OPERATOR_MATCHES.GT.getValue())) {
                //大于
                String fieldName = entry.getKey().substring(0,entry.getKey().indexOf(DB_OPERATOR_MATCHES.GT.getValue()));
                if(paramMap.containsKey(StringUtil.concat(fieldName,DB_OPERATOR_MATCHES.LT.getValue()))){
                    //当存在同字段小于等于时组合
                    criteria.andGreaterThan(fieldName,entry.getValue()).andLessThan(fieldName,paramMap.get(StringUtil.concat(fieldName,DB_OPERATOR_MATCHES.LT.getValue())));
                } else {
                    criteria.andGreaterThan(fieldName,entry.getValue());
                }
            } else if(entry.getKey().contains(DB_OPERATOR_MATCHES.LT.getValue())) {
                //小于
                String fieldName = entry.getKey().substring(0,entry.getKey().indexOf(DB_OPERATOR_MATCHES.LT.getValue()));
                if(paramMap.containsKey(StringUtil.concat(fieldName,DB_OPERATOR_MATCHES.GT.getValue()))){
                    continue;
                }
                criteria.andLessThan(fieldName,entry.getValue());
            } else if(entry.getKey().contains(DB_OPERATOR_MATCHES.BET.getValue())) {
                //范围
                String fieldName = entry.getKey().substring(0,entry.getKey().indexOf(DB_OPERATOR_MATCHES.BET.getValue()));
                List<Object> rangeList = (List<Object>) entry.getValue();
                criteria.andBetween(fieldName,rangeList.get(0),rangeList.get(1));
            } else if(entry.getKey().contains(DB_OPERATOR_MATCHES.BLK.getValue())) {
                //模糊匹配
                String fieldName = entry.getKey().substring(0,entry.getKey().indexOf(DB_OPERATOR_MATCHES.BLK.getValue()));
                criteria.andLike(fieldName,StringUtil.concat("%",entry.getValue().toString(),"%"));
            } else if(entry.getKey().contains(DB_OPERATOR_MATCHES.RLK.getValue())) {
                //右模糊匹配
                String fieldName = entry.getKey().substring(0,entry.getKey().indexOf(DB_OPERATOR_MATCHES.RLK.getValue()));
                criteria.andLike(fieldName,StringUtil.concat(entry.getValue().toString(),"%"));
            } else if(entry.getKey().contains(DB_OPERATOR_MATCHES.LLK.getValue())) {
                //左模糊匹配
                String fieldName = entry.getKey().substring(0,entry.getKey().indexOf(DB_OPERATOR_MATCHES.LLK.getValue()));
                criteria.andLike(fieldName,StringUtil.concat("%",entry.getValue().toString()));
            } else if(entry.getKey().contains(DB_OPERATOR_MATCHES.NE.getValue())) {
                //不等于
                String fieldName = entry.getKey().substring(0,entry.getKey().indexOf(DB_OPERATOR_MATCHES.NE.getValue()));
                criteria.andNotEqualTo(fieldName,entry.getValue());
            } else {
                criteria.andEqualTo(entry.getKey(),entry.getValue());
            }
        }
        return condition;
    }
}
