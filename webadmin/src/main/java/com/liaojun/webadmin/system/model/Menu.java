package com.liaojun.webadmin.system.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;

import javax.persistence.Table;
import java.util.List;

/**
 * @Author: yangzi
 * @Date: 2018/4/12 13:56
 */
@Table(name = "t_menu")
public class Menu{
    @Id
    private String id;
    private String name;
    private String pid;
    private String icon;
    private String url;
    private Integer sort;
    @Transient
    private List<Menu> subMenuList;

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

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public List<Menu> getSubMenuList() {
        return subMenuList;
    }

    public void setSubMenuList(List<Menu> subMenuList) {
        this.subMenuList = subMenuList;
    }
}

