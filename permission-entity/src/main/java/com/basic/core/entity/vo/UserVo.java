package com.basic.core.entity.vo;

import java.util.Date;
import java.util.List;

/**
 * Created by startcaft on 2017/6/23.
 */
public class UserVo extends BaseVo {

    private Integer age;
    private Date createDatetime;
    private String loginName;
    private String name;
    private Integer gender;
    private String genderDesc;
    private Integer statesCode;
    private String statesMsg;
    private Long organizationId;

    private String organizationName;                //所在部门
    private List<Long> roleIds;					    //所属角色编号集合
    private List<String> roleNames;                 //所属角色名集合

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getCreateDatetime() {
        return createDatetime;
    }

    public void setCreateDatetime(Date createDatetime) {
        this.createDatetime = createDatetime;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getGenderDesc() {
        return genderDesc;
    }

    public void setGenderDesc(String genderDesc) {
        this.genderDesc = genderDesc;
    }

    public Integer getStatesCode() {
        return statesCode;
    }

    public void setStatesCode(Integer statesCode) {
        this.statesCode = statesCode;
    }

    public String getStatesMsg() {
        return statesMsg;
    }

    public void setStatesMsg(String statesMsg) {
        this.statesMsg = statesMsg;
    }

    public Long getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Long organizationId) {
        this.organizationId = organizationId;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public List<Long> getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(List<Long> roleIds) {
        this.roleIds = roleIds;
    }

    public List<String> getRoleNames() {
        return roleNames;
    }

    public void setRoleNames(List<String> roleNames) {
        this.roleNames = roleNames;
    }
}
