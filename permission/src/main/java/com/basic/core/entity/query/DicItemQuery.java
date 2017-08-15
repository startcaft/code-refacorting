package com.basic.core.entity.query;

public class DicItemQuery extends BaseQuery {

    private String name;            //过滤数据项名称
    private Long typeId;            //过滤字典类别

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }
}
