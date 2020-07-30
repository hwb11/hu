package com.zy.intelligentdevice.common.enums;

import org.apache.commons.lang.StringUtils;

public class ExceptionEnum {
    //系统异常
    public final static String SYS_ERROR_CODE = "-1";
    //业务异常
    public final static String BUSSINESS_ERROR_CODE = "-2";
    //成功
    public final static boolean SUCESS = true;
    //失败
    public final static boolean FAIL = false;

    public enum SysErrorCodeEnum{
        ErrorCode_ErrorParam("参数错误","-101");
        private String text;
        private String value;
        SysErrorCodeEnum(String text,String value){
            this.text = text;
            this.value = value;
        }
        public String getText() {
            return text;
        }

        public String getValue() {
            return value;
        }
    }








}
