package com.kanasinfo.ext.mvc.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by gefangshuai on 2017/5/24.
 */
public class RequestUtils {
    /**
     * 获取服务器域名
     */
    public static String getDomain(HttpServletRequest request){
        StringBuffer url = request.getRequestURL();
        return url.delete(url.length() - request.getRequestURI().length(), url.length()).append(request.getServletContext().getContextPath()).toString();
    }
}
