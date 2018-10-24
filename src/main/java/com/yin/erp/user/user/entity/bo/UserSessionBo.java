package com.yin.erp.user.user.entity.bo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 用户状态BO
 */
@Getter
@Setter
public class UserSessionBo implements Serializable {

    private String id;

    private String account;

    private String name;

    public UserSessionBo(String id, String account, String name) {
        this.id = id;
        this.account = account;
        this.name = name;
    }

}
