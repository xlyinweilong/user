package com.yin.erp.user.user.entity.bo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * 用户状态BO
 */
@Getter
@Setter
public class UserSessionBo implements Serializable {

    private String account;

    private String name;

    private String avatar = "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif";

    private String token;

    private String introduction;

    private List<String> roles;



}
