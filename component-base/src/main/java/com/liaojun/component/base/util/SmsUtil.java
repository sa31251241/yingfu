package com.liaojun.component.base.util;

import com.alibaba.fastjson.JSONObject;
import com.liaojun.component.base.common.model.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Map;

/**
 * Created by ChamIt-001 on 2017/11/28.
 */
public class SmsUtil {

    private static Logger logger = LoggerFactory.getLogger(SmsUtil.class);
    private Boolean enable = false;
    private String apiurl;
    private String account;
    private String password;
    private String title;

    private static SmsUtil smsUtilInstance;

    public static SmsUtil getInstance(){
        if(smsUtilInstance == null){
            smsUtilInstance = new SmsUtil(PropConfigPool.getString("sms.apiurl"),PropConfigPool.getString("sms.account")
                    ,PropConfigPool.getString("sms.password"),PropConfigPool.getString("sms.title"));
            if(!StringUtil.isEmpty(PropConfigPool.getString("sms.enable")) && PropConfigPool.getString("sms.enable").equals("1")){
                smsUtilInstance.setEnable(true);
            } else {
                smsUtilInstance.setEnable(false);
            }
        }
        return smsUtilInstance;
    }

    public SmsUtil(String url,String account,String password,String title){
        this.apiurl = url;
        this.account = account;
        this.password = password;
        this.title = title;
    }

    public Result sendMessage(String[] mobiles, String content){
        String mobileStr = String.join(",",mobiles);
        String sendContent = StringUtil.concat("【",this.title,"】",content);
        if(!this.enable){
            logger.info("send message:mobile={},context={}",mobileStr,sendContent);
            return Result.EMPTY_SUCC_RESULT;
        }
        try {
            String getUrl = StringUtil.concat(this.apiurl,"?account=",this.account,"&password=", HashUtil.md5(this.password),"&mobile=",mobileStr,"&context=",URLEncoder.encode(sendContent,"UTF-8"));
            String response = HttpUtil.get(getUrl);
            Map<String,Object> result = JSONObject.parseObject(response,Map.class);
            return new Result(result.get("retCode").equals(0),result.get("retMsg").toString());
        } catch (IOException e) {
            logger.error("send message error:mobile={},context={}",mobileStr,sendContent);
            return new Result(false,"发送异常："+e.getMessage());
        }
    }

    public void setEnable(Boolean enable){
        this.enable = enable;
    }
}
