package com.yonyou.demo.utils;

import java.io.Serializable;

public class ServiceResponse<T> implements Serializable {
    private static final long serialVersionUID = 21312493285943435l;
//    private ServiceResponseStatusEnum responeCode;
    private String responeCode;
    private String codeValue;
    private String errorMsg;
    private String detail;
    private T result;





    public String getResponeCode() {
        return responeCode;
    }

    public String getCodeValue() {
        return codeValue;
    }

    public void setResponeCode(ServiceResponseStatusEnum responeCode) {
//        this.responeCode = responeCode;
        this.responeCode = responeCode.getCode();
        this.codeValue = responeCode.getValue();
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}
