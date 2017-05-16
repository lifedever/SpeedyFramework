package io.github.gefangshuai.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

/**
 * properties文件加载器
 * Created by Devid on 2016/12/5.
 */
public final class PropKit {
    /**
     * 获取properties文件
     */
    public static Properties getProps(String propFilePath) throws IOException {
        InputStream inputStream = PropKit.class.getResourceAsStream(propFilePath);
        Properties properties = new Properties();
        properties.load(inputStream);
        return properties;
    }

    /**
     * 根据字符串json形式，获取key对应的Value
     * @param propString
     * @param key
     * @return
     */
    public static String getPropValue(String propString, String key) {
        Map<String, String> props = GsonUtils.getGson().fromJson(propString, Map.class);
        return props.get(key);
    }
}
