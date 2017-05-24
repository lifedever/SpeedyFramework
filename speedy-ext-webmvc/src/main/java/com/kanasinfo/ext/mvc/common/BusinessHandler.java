package com.kanasinfo.ext.mvc.common;

public interface BusinessHandler<T> {
    public T handle() throws Exception;
}
