package com.basic.core.entity.vo;

/**
 * 响应给客户端浏览器的数据类型
 */
public class MsgJson {

    private boolean success = false;
    private Object resultData;
    private String tipInfo;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Object getResultData() {
        return resultData;
    }

    public void setResultData(Object resultData) {
        this.resultData = resultData;
    }

    public String getTipInfo() {
        return tipInfo;
    }
    public void setTipInfo(String tipInfo) {
        this.tipInfo = tipInfo;
    }
}
