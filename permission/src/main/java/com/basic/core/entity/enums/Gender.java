package com.basic.core.entity.enums;

/**
 * Created by startcaft on 2017/6/23.
 */
public enum Gender {

    UNKNOWN(1,"未知的性别"),
    MALE(2,"男性"),
    FEMALE(3,"女性");

    private String desc;
    private Integer code;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    private Gender(Integer code, String desc){
        this.code = code;
        this.desc = desc;
    }

    public static Gender getGender(Integer code){
        {
            switch (code){
                case 2 :
                    return MALE;
                case 3 :
                    return FEMALE;
                case 1:
                    return UNKNOWN;
                default:
                    return UNKNOWN;
            }
        }
    }
}
