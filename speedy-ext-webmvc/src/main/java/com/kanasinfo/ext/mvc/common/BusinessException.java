package com.kanasinfo.ext.mvc.common;

public class BusinessException extends Exception {

    public static final BusinessException DATA_NOT_FOUND = new BusinessException("data_not_found");
    public static final BusinessException DATA_DUPLICATION = new BusinessException("data_duplication");

    private static final long serialVersionUID = 1L;
    private String code;
    private String message;

    public BusinessException newMessage(String msg) {
        BusinessException be = new BusinessException(this.getCode());
        be.setMessage(message);
        return be;
    }

    public BusinessException() {

    }

    public BusinessException(String code, String msg) {
        this.message = msg;
        this.code = code;
    }

    public BusinessException(String code) {
        super();
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
