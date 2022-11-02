package com.example.network;

import java.io.Serializable;

/**
 * 接口返回数据的基类
 */
public class BaseResponseBean implements Serializable {
    private String errorCode;
    private String errorMsg;
    private boolean result;

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }
}
