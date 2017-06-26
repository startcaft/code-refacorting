package com.basic.core.entity.enums;

/**
 * 用户状态枚举
 */
public enum UserStates {

    NORMAL(100,"正常"),
    LOCKED(200,"锁定");

    private String msg;
    private Integer code;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    private UserStates(Integer code, String msg){
        this.code = code;
        this.msg = msg;
    }

    public static UserStates getStates(Integer code){
        switch(code){
            case 100:
                return NORMAL;
            case 200:
                return LOCKED;
            default:
                return NORMAL;
        }
    }
}
