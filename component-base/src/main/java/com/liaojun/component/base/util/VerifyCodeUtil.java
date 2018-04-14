package com.liaojun.component.base.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

/**
 * Created by ChamIt-001 on 2017/11/28.
 */
public class VerifyCodeUtil {

    private Map<String,VerifyCode> verifyCodeMap;
    private Integer CLEAR_SIZE_INTERVAL = 20;
    private Random random = new Random(IdGenerateUtil.getInstance().nextId());

    private static VerifyCodeUtil verifyCodeUtilInstance;

    private VerifyCodeUtil(){
        verifyCodeMap = new HashMap<>();
    }

    public static VerifyCodeUtil getInstance(){
        if(verifyCodeUtilInstance == null){
            verifyCodeUtilInstance = new VerifyCodeUtil();
        }
        return verifyCodeUtilInstance;
    }

    public String create(String key,Long expireSeconds,Long nextCreateSeconds){
        VerifyCode existCode = getCode(key);
        if(existCode != null && System.currentTimeMillis() <= existCode.getNextCreateTime()){
            return null;
        } else {
            VerifyCode code = new VerifyCode();
            code.setCode(String.format("%05d",random.nextInt(90000)+10000));
            code.setNextCreateTime(System.currentTimeMillis() + nextCreateSeconds * 1000);
            code.setExpireTime(System.currentTimeMillis() + expireSeconds * 1000);
            verifyCodeMap.put(key,code);
            if(verifyCodeMap.size() % CLEAR_SIZE_INTERVAL == 0){
                clearExpireCodes();
            }
            return code.getCode();
        }
    }

    public Boolean validate(String key,String code){
        VerifyCode verifyCode = getCode(key);
        if(verifyCode == null){
            return false;
        }
        if(code.equals(verifyCode.getCode())){
            verifyCodeMap.remove(key);
            return true;
        } else {
            return false;
        }
    }

    public void clearExpireCodes(){
        Iterator iterator = verifyCodeMap.entrySet().iterator();
        while (iterator.hasNext()){
            VerifyCode verifyCode = (VerifyCode) iterator.next();
            if(System.currentTimeMillis() > verifyCode.getExpireTime()){
                iterator.remove();
            }
        }
    }

    private VerifyCode getCode(String key){
        VerifyCode verifyCode = verifyCodeMap.get(key);
        if(verifyCode == null){
            return null;
        }
        if(System.currentTimeMillis() > verifyCode.getExpireTime()){
            verifyCodeMap.remove(key);
            return null;
        }
        return verifyCode;
    }

}
class VerifyCode {

    private String code;
    private Long nextCreateTime;
    private Long expireTime;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Long getNextCreateTime() {
        return nextCreateTime;
    }

    public void setNextCreateTime(Long nextCreateTime) {
        this.nextCreateTime = nextCreateTime;
    }

    public Long getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Long expireTime) {
        this.expireTime = expireTime;
    }
}