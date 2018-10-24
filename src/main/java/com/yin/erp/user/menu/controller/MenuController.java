package com.yin.erp.user.menu.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;

/**
 * 菜单控制器
 */
@RestController
@RequestMapping(value = "menu")
@Transactional
public class MenuController {

    //my menus

    //my powers


//    @Autowired
//    private MenuRepository menuRepository;
//    @Autowired
//    private UserMenuRepository userMenuRepository;
//
//    @RequestMapping(method = RequestMethod.GET)
//    public MenuVo getMyMenuList(HttpSession session) throws Exception {
//        UserSessionBo userSessionBo = (UserSessionBo) session.getAttribute("user");
//        List<UserMenu> userMenuList = userMenuRepository.findByUserId(userSessionBo.getId());
//        MenuVo menuVo = new MenuVo();
//        return menuVo;
////        userSessionBo.getId();
//    }
}
