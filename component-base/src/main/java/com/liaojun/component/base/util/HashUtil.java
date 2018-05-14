package com.liaojun.component.base.util;

import com.liaojun.component.base.common.model.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.BASE64Encoder;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by ChamIt-001 on 2017/10/24.
 */
public class HashUtil {

    private static final Logger logger = LoggerFactory.getLogger(HashUtil.class);

    public static String md5(String content){
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(content.getBytes());
            return new BigInteger(1, md.digest()).toString(16);
        } catch (NoSuchAlgorithmException e) {
            throw new BusinessException(e);
        }
    }

    public static String md5WithSalt(String content,String salt){
        try {
            return new BASE64Encoder().encode(MessageDigest.getInstance("MD5").digest((content + salt).getBytes()));
        } catch (NoSuchAlgorithmException e) {
            throw new BusinessException(e);
        }
    }
}
