package com.zy.intelligentdevice.common.exception;

/**
 * 系统异常基类
 */
public class SyException extends RuntimeException{

    private String errorCode;

    public SyException(String errorCcode,String errorMsg){
        super(errorMsg);
        this.errorCode = errorCcode;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

}
