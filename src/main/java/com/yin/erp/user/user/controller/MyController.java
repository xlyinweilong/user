package com.yin.erp.user.user.controller;

import com.yin.erp.base.controller.BaseJson;
import com.yin.erp.base.exceptions.MessageException;
import com.yin.erp.user.user.dao.UserDao;
import com.yin.erp.user.user.entity.bo.UserSessionBo;
import com.yin.erp.user.user.entity.po.UserPo;
import com.yin.erp.user.user.entity.vo.LoginUserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpSession;

/**
 * 用户控制器
 */
@RestController
@RequestMapping(value = "user")
//@ExceptionHandlerAnnotation
public class MyController {

    //    @Autowired
//    private UserService userService;
    @Autowired
    private UserDao userDao;

    /**
     * 登录
     *
     * @param loginUserVo
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "login", method = RequestMethod.POST, consumes = "application/json")
//    @ExceptionHandlerAnnotation
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
        session.setAttribute("user", new UserSessionBo(user.getId(), user.getAccount(), user.getName()));
        return BaseJson.getSuccess(loginUserVo);
    }

    /**
     * 个人信息信息
     *
     * @param session
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "info", method = RequestMethod.GET)
//    @ExceptionHandlerAnnotation
    public BaseJson info(HttpSession session) throws Exception {
        return BaseJson.getSuccess("123");
    }

    /**
     * 登出
     *
     * @param session
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "logout", method = RequestMethod.POST)
//    @ExceptionHandlerAnnotation
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
//    @ExceptionHandlerAnnotation
    public BaseJson resetPassword(HttpSession session, @RequestBody String password) throws Exception {
        UserSessionBo userBo = (UserSessionBo) session.getAttribute("user");
        UserPo user = userDao.findById(userBo.getId()).get();
        user.setPasswd(password);
        userDao.save(user);
        return BaseJson.getSuccess();
    }

    //用户菜单

    //用户权力

}
