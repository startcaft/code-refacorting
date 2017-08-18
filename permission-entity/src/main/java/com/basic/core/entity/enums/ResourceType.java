package com.basic.core.entity.enums;


/**
 * 系统资源类别枚举类。<br/>
 * 功能菜单，
 * 功能按钮
 */
public enum ResourceType {

    MENU(1,"功能菜单"),
    FUNC(2,"功能按钮");

    private Integer code;
    private String desc;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    private ResourceType(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static ResourceType getResourceType(Integer resTypeCode){
        switch (resTypeCode){
            case 1:
                return MENU;
            case 2:
                return FUNC;
            default:
                    return MENU;
        }
    }
}
