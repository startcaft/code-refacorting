package com.basic.core.entity.vo;

import java.util.Date;

/**
 * Created by startcaft on 2017/6/30.
 */
public class ResourceVo extends BaseVo {

    private Date createDatetime;
    private String description;
    private String icon;
    private String name;
    private Integer resTypeCode;
    private String resTypeMsg;
    private Integer seq;
    private Integer statesCode;
    private String statesMsg;
    private String url;
    private Long pid;
    private Long appId;


    private Integer level;       //层级
    private boolean isLeaf;     //是否叶节点
    private boolean expanded;    //是否展开

    private boolean isRoot;     //是否顶级，只能是菜单
    private String pname;       //父资源节点名称
    private boolean isShared;   //是否公共资源

    private boolean checked;    //是否选中

    public Date getCreateDatetime() {
        return createDatetime;
    }

    public void setCreateDatetime(Date createDatetime) {
        this.createDatetime = createDatetime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getResTypeCode() {
        return resTypeCode;
    }

    public void setResTypeCode(Integer resTypeCode) {
        this.resTypeCode = resTypeCode;
    }

    public String getResTypeMsg() {
        return resTypeMsg;
    }

    public void setResTypeMsg(String resTypeMsg) {
        this.resTypeMsg = resTypeMsg;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public boolean isLeaf() {
        return isLeaf;
    }

    public void setLeaf(boolean leaf) {
        isLeaf = leaf;
    }

    public boolean getExpanded() {
        return expanded;
    }

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }

    public boolean isExpanded() {
        return expanded;
    }

    public boolean isRoot() {
        return isRoot;
    }

    public void setRoot(boolean root) {
        isRoot = root;
    }

    public boolean isShared() {
        return isShared;
    }

    public void setShared(boolean shared) {
        isShared = shared;
    }

    public Long getAppId() {
        return appId;
    }

    public void setAppId(Long appId) {
        this.appId = appId;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
