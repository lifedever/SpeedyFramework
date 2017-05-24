package com.kanasinfo.ext.mvc.utils;


import com.kanasinfo.utils.EncryptUtils;

/**
 * 进行请求校验
 * Created by user on 2016/9/17.
 */
public final class HttpVerifyUtils {
    /**
     * 规则：“httpBody + {salt}。（盐值）”做sha1摘要
     */
    public static String getDigestValue(String httpBody, String secretKey) {
        String value = httpBody + String.format("{%s}", secretKey);
        String sha1Value = EncryptUtils.sha1(value);
        return sha1Value;
    }
}
