package com.yin.erp.user.menu.entity.po;

import com.yin.erp.base.entity.po.BasePo;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

/**
 * 菜单表
 */
@Entity
@Table(name = "u_menu")
@Getter
@Setter
public class Menu extends BasePo {


    @Column(name = "title")
    private String title;

    @Column(name = "sort")
    private int sort;

    @Column(name = "url")
    private String url;

    @Column(name = "pid")
    private long pid;

    @Column(name = "deleted")
    private boolean deleted;

}
