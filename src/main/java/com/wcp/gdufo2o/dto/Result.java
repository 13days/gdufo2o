package com.wcp.gdufo2o.dto;

/**
 * 封装json对象，所有返回结果都使用它
 * @param <T>
 */
public class Result<T> {

    private boolean success;

    private T data;

    private String errorMsg;

    private int errorCode;

    public Result(){}

    // 正常构造器
    public Result(boolean success, T data){
        this.data = data;
        this.success = success;
    }

    // 错误时的构造器
    public Result(boolean success, int errorCode, String errorMsg) {
        this.success = success;
        this.errorMsg = errorMsg;
        this.errorCode = errorCode;
    }


    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }
}
