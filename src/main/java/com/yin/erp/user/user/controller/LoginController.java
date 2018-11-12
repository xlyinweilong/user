package com.yin.erp.user.user.controller;

import com.yin.erp.base.controller.BaseJson;
import com.yin.erp.base.exceptions.MessageException;
import com.yin.erp.user.user.dao.UserDao;
import com.yin.erp.user.user.entity.bo.UserSessionBo;
import com.yin.erp.user.user.entity.po.UserPo;
import com.yin.erp.user.user.entity.vo.in.LoginUserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * 用户控制器
 *
 * @author yin
 */
@RestController
@RequestMapping(value = "login")
public class LoginController {

    @Autowired
    private UserDao userDao;

    /**
     * 登录
     *
     * @param loginUserVo
     * @return
     * @throws Exception
     */
    @PostMapping(value = "login", consumes = "application/json")
    public BaseJson login(@Validated @RequestBody LoginUserVo loginUserVo, HttpSession session) throws Exception {
        UserPo user = userDao.findByAccount(loginUserVo.getUsername());
        if (user == null) {
            throw new MessageException("账号错误！");
        }
        if (!user.getPasswd().equals(loginUserVo.getPassword())) {
            throw new MessageException("密码错误！");
        }
        loginUserVo.setPassword(null);
        loginUserVo.setName(user.getName());
        loginUserVo.setToken(session.getId());
        UserSessionBo bo = new UserSessionBo();
        bo.setToken("admin");
        bo.setName(user.getName());
        List<String> roles = new ArrayList<>();
        roles.add("admin");
        bo.setRoles(roles);
        session.setAttribute("user", bo);
        return BaseJson.getSuccess("登录成功", bo);
    }

    /**
     * 个人信息信息
     *
     * @param session
     * @return
     * @throws Exception
     */
    @GetMapping(value = "info")
    public BaseJson info(HttpSession session) throws Exception {
        UserSessionBo userBo = (UserSessionBo) session.getAttribute("user");
        return BaseJson.getSuccess(userBo);
    }

    /**
     * 登出
     *
     * @param session
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "logout", method = RequestMethod.POST)
    public BaseJson logout(HttpSession session) throws Exception {
        session.removeAttribute("user");
        return BaseJson.getSuccess();
    }

    /**
     * 重置密码
     *
     * @param session
     * @param password
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "reset_password", method = RequestMethod.POST, consumes = "application/json")
    public BaseJson resetPassword(HttpSession session, @RequestBody String password) throws Exception {
        UserSessionBo userBo = (UserSessionBo) session.getAttribute("user");
//        UserPo user = userDao.findById(userBo.getId()).get();
//        user.setPasswd(password);
//        userDao.save(user);
        return BaseJson.getSuccess();
    }

    //用户菜单

    //用户权力

}
