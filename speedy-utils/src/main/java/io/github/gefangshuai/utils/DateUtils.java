package io.github.gefangshuai.utils;

import org.joda.time.DateTime;

import java.util.Date;

/**
 * 日期处理相关类
 * Created by Devid on 2016/12/2.
 */
public class DateUtils {
    public static String format(Date date, String pattern) {
        return date == null ? "" : new DateTime(date).toString(pattern);
    }
}
