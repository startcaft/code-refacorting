package com.basic.core.entity;

import java.util.ArrayList;
import java.util.List;

public class Role extends BaseEntity {
	
    private String description;
    private String name;
    private Integer seq;
    
    private List<Resource> resources = new ArrayList<Resource>(0);			//所包含资源
	private List<User> users = new ArrayList<User>(0);						//所对应用户

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

	public List<Resource> getResources() {
		return resources;
	}

	public void setResources(List<Resource> resources) {
		this.resources = resources;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
}