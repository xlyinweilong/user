package com.yin.erp.user.info.dict.entity.vo.in;

import com.yin.erp.base.entity.vo.in.BaseDeleteVo;
import lombok.Getter;
import lombok.Setter;

/**
 * 字段删除VO
 *
 * @author yin
 */
@Getter
@Setter
public class DictDeleteVo extends BaseDeleteVo {

    private String type1;

    private String type2;

}
