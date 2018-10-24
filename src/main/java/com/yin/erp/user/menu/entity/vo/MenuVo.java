package com.yin.erp.user.menu.entity.vo;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import java.util.List;

/**
 * 菜单
 */
public class MenuVo {

    private long id;

    @NotBlank
    @Length(min = 1, max = 255)
    private String title;

    private int sort;

    private String url;

    private List<MenuVo> subMenuList;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<MenuVo> getSubMenuList() {
        return subMenuList;
    }

    public void setSubMenuList(List<MenuVo> subMenuList) {
        this.subMenuList = subMenuList;
    }
}
