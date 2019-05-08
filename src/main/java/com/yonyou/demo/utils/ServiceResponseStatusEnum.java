package com.yonyou.demo.utils;

public enum ServiceResponseStatusEnum {

    SUCCESS("success","成功"),
    FAILURE("failure","失败")
    ;

    private  String code;
    private String value;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    ServiceResponseStatusEnum(String code, String value) {
        this.code = code;
        this.value = value;
    }
}
