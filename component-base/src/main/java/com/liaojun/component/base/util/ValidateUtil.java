package com.liaojun.component.base.util;

import java.math.BigDecimal;

/**
 * Created by ChamIt-001 on 2017/6/14.
 */
public class ValidateUtil {

    public static enum NUMERIC_STATUS {
        ALL,POSITIVE,NEGATIVE,ABOVE
    }

    public static Boolean isNumeric(Object obj,NUMERIC_STATUS numericStatus){
        if(obj == null){
            return false;
        }
        if(numericStatus == NUMERIC_STATUS.POSITIVE){
            return new BigDecimal(obj.toString()).compareTo(BigDecimal.ZERO)>=0;
        } else if(numericStatus == NUMERIC_STATUS.NEGATIVE){
            return new BigDecimal(obj.toString()).compareTo(BigDecimal.ZERO)<=0;
        } else if(numericStatus == NUMERIC_STATUS.ABOVE){
            return new BigDecimal(obj.toString()).compareTo(BigDecimal.ZERO)>0;
        }
        return true;
    }
}
