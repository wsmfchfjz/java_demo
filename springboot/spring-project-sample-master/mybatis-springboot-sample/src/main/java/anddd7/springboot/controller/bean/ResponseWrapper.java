package anddd7.springboot.controller.bean;

import anddd7.springboot.controller.common.ResponseEnum;

public class ResponseWrapper<T> {
    Integer resultCode;
    String resultMsg;
    T resultObj;

    public ResponseWrapper(ResponseEnum resEnum) {
        this.resultCode = resEnum.getCode();
        this.resultMsg = resEnum.getMsg();
    }

    public ResponseWrapper(ResponseEnum resEnum, String msg, T obj) {
        this.resultCode = resEnum.getCode();
        this.resultMsg = msg;
        this.resultObj = obj;
    }

    public ResponseWrapper(ResponseEnum resEnum, T obj) {
        this.resultCode = resEnum.getCode();
        this.resultMsg = resEnum.getMsg();
        this.resultObj = obj;
    }

    public Integer getResultCode() {
        return resultCode;
    }

    public void setResultCode(Integer resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }

    public T getResultObj() {
        return resultObj;
    }

    public void setResultObj(T resultObj) {
        this.resultObj = resultObj;
    }
}
