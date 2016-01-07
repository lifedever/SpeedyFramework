package io.github.gefangshuai.ext.shiro.utils;

import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;

/**
 * 安全有关
 * Created by gefangshuai on 2015/11/9.
 */
public class HashUtils {

    /**
     * 获取一个随机的盐值
     *
     * @return
     */
    public static String generateSalt() {
        return new SecureRandomNumberGenerator().nextBytes().toHex();
    }

    /**
     * 获取加密后的hash值
     * @param password
     * @param salt
     * @return
     */
    public static String toMd5(String password, String salt) {
        SimpleHash hash = new SimpleHash("md5", password, salt, 2);
        return hash.toHex();
    }
}
