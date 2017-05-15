package io.github.gefangshuai.ext.mvc.common;

public interface BusinessHandler<T> {
    public T handle() throws Exception;
}
