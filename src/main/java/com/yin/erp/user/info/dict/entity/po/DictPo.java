package com.yin.erp.user.info.dict.entity.po;

import com.yin.erp.base.entity.po.BasePo;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * 字典
 *
 * @author yin
 */
@Entity
@Table(name = "dict", uniqueConstraints = {@UniqueConstraint(columnNames = {"code", "name", "type1", "type2"})})
@Getter
@Setter
public class DictPo extends BasePo {

    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    @Column(name = "type1")
    private String type1;

    @Column(name = "type2")
    private String type2;


}
