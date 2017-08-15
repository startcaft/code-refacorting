package com.basic.core.entity.query;


public class UserQuery extends BaseQuery {

    private String name;//查询姓名
    private Long orgId;//查询组织

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }
}
