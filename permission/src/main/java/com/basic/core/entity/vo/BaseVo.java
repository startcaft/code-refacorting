package com.basic.core.entity.vo;

/**
 * 数据Vo类，直接响应至客户端
 */
public abstract class BaseVo implements java.io.Serializable {

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
