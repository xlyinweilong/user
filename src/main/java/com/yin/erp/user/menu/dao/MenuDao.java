package com.yin.erp.user.menu.dao;


import com.yin.erp.user.menu.entity.po.Menu;
import org.springframework.data.repository.PagingAndSortingRepository;

import javax.transaction.Transactional;

/**
 * 菜单仓库
 */
//@RepositoryRestResource(collectionResourceRel = "menu", path = "menu")
@Transactional
public interface MenuDao extends PagingAndSortingRepository<Menu, Long> {


//    List<Menu> findById(@Param("id") long id);

//    UserPo findByAccount(String account);

//    @RequestMapping("/delete")
//    @Query("update Menu u set u.delete = true")@

//    int update(@Param("id") long id);

}
