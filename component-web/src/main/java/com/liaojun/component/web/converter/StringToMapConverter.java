package com.liaojun.component.web.converter;

import com.alibaba.fastjson.JSONObject;
import com.liaojun.component.base.util.StringUtil;
import org.springframework.core.convert.converter.Converter;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Map;

/**
 * Created by ChamIt-001 on 2017/10/24.
 */
public class StringToMapConverter implements Converter<String,Map>{
    @Override
    public Map convert(String s) {
        if(StringUtil.isEmpty(s)){
            return null;
        }
        //判断是否已经urldecode
        if(s.indexOf("%7b")>=0){
            try {
                s = URLDecoder.decode(s,"UTF-8");
            } catch (UnsupportedEncodingException e) {
                return null;
            }
        }
        return JSONObject.parseObject(s,Map.class);
    }
}
