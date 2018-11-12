package com.yin.erp.user.info.supplier.dao;


import com.yin.erp.user.info.supplier.entity.po.SupplierPo;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import javax.annotation.Resource;

/**
 * 供应商
 *
 * @author yin
 */
@Resource
public interface SupplierDao extends PagingAndSortingRepository<SupplierPo, String>, JpaSpecificationExecutor {

}
