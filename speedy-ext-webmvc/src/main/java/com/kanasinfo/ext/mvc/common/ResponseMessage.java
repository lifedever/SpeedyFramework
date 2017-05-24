package com.kanasinfo.ext.mvc.common;

import org.apache.log4j.Logger;

import java.io.Serializable;


public class ResponseMessage<T> implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public static final String SUCCESS = "success";
    public static final String ERROR = "error";
    public static final String WARNING = "warning";

    public static final <T> ResponseMessage<T> handleException(BusinessHandler<T> bh, Logger logger) {
        ResponseMessage<T> rm = new ResponseMessage<T>();
        try {
            rm.setMessage(bh.handle());
            rm.setStatus(ResponseMessage.SUCCESS);
        } catch (BusinessException be) {
            rm.setExceptionCode(be.getCode());
            rm.setExceptionMessage(be.getMessage());
            rm.setStatus(ResponseMessage.WARNING);
            if (logger != null && logger.isDebugEnabled()) logger.debug(be.getMessage());
        } catch (Exception e) {
            rm.setExceptionCode(e.getClass().getCanonicalName());
            rm.setExceptionMessage(e.getMessage());
            rm.setStatus(ResponseMessage.ERROR);
            logger.info(e);
        }
        return rm;
    }

    private String status;

    private String exceptionCode;

    private String exceptionMessage;

    private T message;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public T getMessage() {
        return message;
    }

    public void setMessage(T message) {
        this.message = message;
    }

    public String getExceptionCode() {
        return exceptionCode;
    }

    public void setExceptionCode(String exceptionCode) {
        this.exceptionCode = exceptionCode;
    }

    public String getExceptionMessage() {
        return exceptionMessage;
    }

    public void setExceptionMessage(String exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer("{");
        sb.append("status:\"")
                .append(status == null ? "" : status.replaceAll("\\\"", "\\\\\\\"").replaceAll("\n", "\\\\n").replaceAll("\r", "\\\\r"))
                .append("\",message:\"")
                .append(message == null ? "" : message.toString().replaceAll("\\\"", "\\\\\\\"").replaceAll("\n", "\\\\n").replaceAll("\r", "\\\\r"))
                .append("\"}");
        return sb.toString();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((exceptionCode == null) ? 0 : exceptionCode.hashCode());
        result = prime * result + ((message == null) ? 0 : message.hashCode());
        result = prime * result + ((status == null) ? 0 : status.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ResponseMessage<?> other = (ResponseMessage<?>) obj;
        if (exceptionCode == null) {
            if (other.exceptionCode != null)
                return false;
        } else if (!exceptionCode.equals(other.exceptionCode))
            return false;
        if (message == null) {
            if (other.message != null)
                return false;
        } else if (!message.equals(other.message))
            return false;
        if (status == null) {
            if (other.status != null)
                return false;
        } else if (!status.equals(other.status))
            return false;
        return true;
    }
}
