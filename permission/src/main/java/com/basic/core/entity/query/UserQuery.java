package com.basic.core.entity.query;

import javax.ws.rs.QueryParam;

public class UserQuery extends BasicQuery {

    @QueryParam("username")
    private String name;//查询姓名
    @QueryParam("org")
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
