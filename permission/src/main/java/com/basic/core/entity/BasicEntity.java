package com.basic.core.entity;

/**
 * 实体基类，包含一个Long类型的id属性，表示一个主键字段
 */
public abstract class BasicEntity {

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
