package com.liaojun.component.base.service;

import com.liaojun.component.base.util.StringUtil;
import org.springframework.validation.ValidationUtils;

import java.math.BigDecimal;
import java.util.regex.Pattern;

/**
 * Created by ChamIt-001 on 2017/12/27.
 */
public class Validator extends ValidationUtils {

    private static final Pattern PATTERN_INT = Pattern.compile("^[-\\+]?[\\d]+$");
    private static final Pattern PATTERN_NUM = Pattern.compile("^[-\\+]?[\\d]+\\.?[\\d]*$");
    private static final Pattern PATTERN_DATE = Pattern.compile("^(\\d{4}|\\d{2})(\\-|\\/)\\d{1,2}(\\-|\\/)\\d{1,2}$");
    private static final Pattern PATTERN_DATETIME = Pattern.compile("^(\\d{4}|\\d{2})(\\-|\\/)\\d{1,2}(\\-|\\/)\\d{1,2} \\d{1,2}:\\d{1,2}:\\d{1,2}$");
    private static final Pattern PATTERN_MOBILE = Pattern.compile("^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(18[0,5-9]))\\d{8}$");
    private static final Pattern PATTERN_PASSWORD = Pattern.compile("^([A-Z]|[a-z]|[0-9]|[`~!@#$%^&*()+=|{}':;',\\\\[\\\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“'。，、？]){6,20}$");
    private static final Pattern PATTERN_EMAIL = Pattern.compile("^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$");

    private Validator(){}

    public static enum CHECK_TYPE {
        EMPTY("empty"),NOT_EMPTY("notEmpty"),EMPTY_SIZE("emptySize")
        ,INT("int"),INT_GT_ZERO("intGTZero"),INT_GTE_ZERO("intGTEZero")
        ,NUM("num"),NUM_GT_ZERO("numGTZero"),NUM_GTE_ZERO("numGTEZero")
        ,GT_ZERO("GTZero"),GTE_ZERO("GTEZero"),DATE("date"),DATETIME("datetime")
        ,MOBILE("mobile"),EMAIL("email"),PASSWORD("password");
        private String value;
        CHECK_TYPE(String value){this.value = value;}
        public String getValue(){return this.value;}
    }

    public static void rejectIfTrue(Boolean checkResult,String errmsg){
        if(checkResult){
            throw ExceptionFactory.createCheckError(errmsg);
        }
    }

    public static void rejectIfFalse(Boolean checkResult,String errmsg){
        if(!checkResult){
            throw ExceptionFactory.createCheckError(errmsg);
        }
    }

    public static void checkEmpty(Object checkObj,String errmsg){
        rejectIfTrue(checkObj == null || (checkObj instanceof String && StringUtil.isEmpty(checkObj)),errmsg);
    }

    public static void checkNotEmpty(Object checkObj,String errmsg){
        rejectIfFalse(checkObj == null || (checkObj instanceof String && StringUtil.isEmpty(checkObj)),errmsg);
    }

    public static void checkEmptySize(Iterable checkObj,String errmsg){
        rejectIfTrue(checkObj == null || !checkObj.iterator().hasNext(),errmsg);
    }

    public static void checkInt(Object checkObj,String errmsg){
        rejectIfTrue(checkObj == null || !PATTERN_INT.matcher(checkObj.toString()).matches(),errmsg);
    }

    public static void checkIntGTZero(Object checkObj,String errmsg){
        rejectIfTrue(checkObj == null || !StringUtil.isNumeric(checkObj.toString()) || Integer.valueOf(checkObj.toString()) <= 0,errmsg);
    }

    public static void checkIntGTEZero(Object checkObj,String errmsg){
        rejectIfTrue(checkObj == null || !StringUtil.isNumeric(checkObj.toString()) || Integer.valueOf(checkObj.toString()) < 0,errmsg);
    }

    public static void checkNum(Object checkObj,String errmsg){
        rejectIfTrue(checkObj == null || !PATTERN_NUM.matcher(checkObj.toString()).matches(),errmsg);
    }

    public static void checkNumGTZero(Object checkObj,String errmsg){
        rejectIfTrue(checkObj == null || !PATTERN_NUM.matcher(checkObj.toString()).matches() || Double.valueOf(checkObj.toString()) <= 0,errmsg);
    }

    public static void checkNumGTEZero(Object checkObj,String errmsg){
        rejectIfTrue(checkObj == null || !PATTERN_NUM.matcher(checkObj.toString()).matches() || Double.valueOf(checkObj.toString()) < 0,errmsg);
    }

    public static void checkGTZero(Integer checkObj,String errmsg){
        rejectIfTrue(checkObj == null || checkObj <= 0,errmsg);
    }

    public static void checkGTEZero(Integer checkObj,String errmsg){
        rejectIfTrue(checkObj == null || checkObj < 0,errmsg);
    }

    public static void checkGTZero(BigDecimal checkObj, String errmsg){
        rejectIfTrue(checkObj == null || checkObj.compareTo(BigDecimal.ZERO) <= 0,errmsg);
    }

    public static void checkGTEZero(BigDecimal checkObj, String errmsg){
        rejectIfTrue(checkObj == null || checkObj.compareTo(BigDecimal.ZERO) < 0,errmsg);
    }

    public static void checkDate(String checkObj,String errmsg){
        rejectIfTrue(checkObj == null || !PATTERN_DATE.matcher(checkObj.toString()).matches(),errmsg);
    }

    public static void checkDateTime(String checkObj,String errmsg){
        rejectIfTrue(checkObj == null || !PATTERN_DATETIME.matcher(checkObj.toString()).matches(),errmsg);
    }

    public static void checkMobile(String checkObj,String errmsg){
        rejectIfTrue(checkObj == null || !PATTERN_MOBILE.matcher(checkObj.toString()).matches(),errmsg);
    }

    public static void checkPassword(String checkObj,String errmsg){
        rejectIfTrue(checkObj == null || !PATTERN_PASSWORD.matcher(checkObj.toString()).matches(),errmsg);
    }

    public static void checkEmail(String checkObj,String errmsg){
        rejectIfTrue(checkObj == null || !PATTERN_EMAIL.matcher(checkObj.toString()).matches(),errmsg);
    }

    public static void check(Object checkObj,String checkType,String errmsg){
        if(checkType.equals(CHECK_TYPE.EMPTY.getValue())){
            checkEmpty(checkObj,errmsg);
        } else if(checkType.equals(CHECK_TYPE.NOT_EMPTY.getValue())){
            checkNotEmpty(checkObj,errmsg);
        } else if(checkType.equals(CHECK_TYPE.EMPTY_SIZE.getValue())){
            checkEmptySize((Iterable) checkObj,errmsg);
        } else if(checkType.equals(CHECK_TYPE.INT.getValue())){
            checkInt(checkObj,errmsg);
        } else if(checkType.equals(CHECK_TYPE.INT_GT_ZERO.getValue())){
            checkIntGTZero(checkObj,errmsg);
        } else if(checkType.equals(CHECK_TYPE.INT_GTE_ZERO.getValue())){
            checkIntGTEZero(checkObj,errmsg);
        } else if(checkType.equals(CHECK_TYPE.NUM.getValue())){
            checkNum(checkObj,errmsg);
        } else if(checkType.equals(CHECK_TYPE.NUM_GT_ZERO.getValue())){
            checkNumGTZero(checkObj,errmsg);
        } else if(checkType.equals(CHECK_TYPE.NUM_GTE_ZERO.getValue())){
            checkNumGTEZero(checkObj,errmsg);
        } else if(checkType.equals(CHECK_TYPE.GT_ZERO.getValue())){
            if(checkObj instanceof Integer) {
                checkGTZero((Integer) checkObj, errmsg);
            } else if(checkObj instanceof BigDecimal) {
                checkGTZero((BigDecimal) checkObj, errmsg);
            }
        } else if(checkType.equals(CHECK_TYPE.GTE_ZERO.getValue())){
            if(checkObj instanceof Integer) {
                checkGTEZero((Integer) checkObj, errmsg);
            } else if(checkObj instanceof BigDecimal) {
                checkGTEZero((BigDecimal) checkObj, errmsg);
            }
        } else if(checkType.equals(CHECK_TYPE.DATE.getValue())){
            checkDate(checkObj.toString(),errmsg);
        } else if(checkType.equals(CHECK_TYPE.DATETIME.getValue())){
            checkDateTime(checkObj.toString(),errmsg);
        } else if(checkType.equals(CHECK_TYPE.MOBILE.getValue())){
            checkMobile(checkObj.toString(),errmsg);
        } else if(checkType.equals(CHECK_TYPE.PASSWORD.getValue())){
            checkPassword(checkObj.toString(),errmsg);
        } else if(checkType.equals(CHECK_TYPE.EMAIL.getValue())){
            checkEmail(checkObj.toString(),errmsg);
        }
    }

}
