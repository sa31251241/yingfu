package com.liaojun.component.base.util;

import org.apache.commons.lang3.StringUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by ChamIt-001 on 2017/5/10.
 */
public class StringUtil extends StringUtils {

    private static final String FULL_HEX_PREFIX = "0x";
    private static final String FULL_HEX_PREFIX_PLUSZERO = "0x0";

    /**
     * 判断字符串非空
     * @param obj
     * @return
     */
    public static boolean isEmpty(Object obj){
        if(obj != null && !isEmpty(obj.toString())){
            return false;
        }
        return true;
    }

    /**
     * 拼接字符串
     * @param strArray
     * @return
     */
    public static String concat(String... strArray){
        StringBuilder sb = new StringBuilder();
        for(String str:strArray){
            sb.append(str);
        }
        return sb.toString();
    }

    public static String[] bytesToIntString(byte[] byts){
        String[] strs = new String[byts.length];
        int index = 0;
        for(byte byt:byts){
            strs[index++] = Integer.toString(byt);
        }
        return strs;
    }

    public static String read(InputStream inputStream) throws IOException {
        ByteArrayOutputStream result = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int length;
        while ((length = inputStream.read(buffer)) != -1) {
            result.write(buffer, 0, length);
        }
        return result.toString("UTF-8");
    }
}
