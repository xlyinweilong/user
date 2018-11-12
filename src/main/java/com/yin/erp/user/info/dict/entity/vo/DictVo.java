package com.yin.erp.user.info.dict.entity.vo;

import com.yin.erp.base.entity.vo.in.BasePageVo;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 字段VO
 *
 * @author yin
 */
@Getter
@Setter
public class DictVo extends BasePageVo {

    private String id;

    private String code;

    private String name;

    private String type1;

    private String type2;

    private List<String> sizeList;

}
