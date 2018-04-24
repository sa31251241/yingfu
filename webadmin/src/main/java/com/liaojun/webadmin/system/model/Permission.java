package com.liaojun.webadmin.system.model;

import javax.persistence.Table;

/**
 * @Author: yangzi
 * @Date: 2018/4/12 13:45
 */
@Table(name = "t_permission")
public class Permission{

    private String id;

    private String name;

    /**
     *   menu button
     */
    private String resourceType;

    private String resourceId;

    private String code;

    private Integer available;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getAvailable() {
        return available;
    }

    public void setAvailable(Integer available) {
        this.available = available;
    }
}
