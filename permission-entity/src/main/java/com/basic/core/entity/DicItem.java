package com.basic.core.entity;

public class DicItem extends BaseEntity {

    private String name;

    private String value;

    private Integer seq;

    private Long dicTypeId;

    private String remark;

    private DicType dicType;            //所属字典类别

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value == null ? null : value.trim();
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public Long getDicTypeId() {
        return dicTypeId;
    }

    public void setDicTypeId(Long dicTypeId) {
        this.dicTypeId = dicTypeId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public DicType getDicType() {
        return dicType;
    }

    public void setDicType(DicType dicType) {
        this.dicType = dicType;
    }
}