package com.yin.erp.user.info.dict.dao;


import com.yin.erp.user.info.dict.entity.po.DictPo;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import javax.annotation.Resource;

/**
 * 字典
 *
 * @author yin
 */
@Resource
public interface DictDao extends PagingAndSortingRepository<DictPo, String>, JpaSpecificationExecutor {

}
