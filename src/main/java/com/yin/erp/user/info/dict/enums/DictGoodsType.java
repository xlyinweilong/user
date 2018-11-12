package com.yin.erp.user.info.dict.enums;

import java.util.*;

/**
 * 货品字典类型
 *
 * @author yin
 */
public enum DictGoodsType {
    COLOR, IN_SIZE, SIZE_GROUP, CATEGORY, CATEGORY_2, SERIES, PATTERN, STYLE, YEAR, SEASON, SEX;

    /**
     * 判断是否含有编号
     *
     * @return true有编号 false没有编号
     */
    public boolean isHaveCode() {
        DictGoodsType[] d = new DictGoodsType[]{COLOR};
        return Arrays.asList(d).contains(this);
    }

    public String getMean() {
        switch (this) {
            case COLOR:
                return "颜色";
            case IN_SIZE:
                return "内长";
            case SIZE_GROUP:
                return "尺码组";
            case CATEGORY:
                return "类别";
            case CATEGORY_2:
                return "二级类别";
            case SERIES:
                return "系列";
            case PATTERN:
                return "款式";
            case STYLE:
                return "风格";
            case YEAR:
                return "年份";
            case SEASON:
                return "季节";
            case SEX:
                return "性别";
            default:
                return null;
        }
    }

    public static List<Map> getMeanList() {
        List<Map> list = new ArrayList<>();
        for (DictGoodsType type : DictGoodsType.values()) {
            Map m = new HashMap(3);
            m.put("key", type.name());
            m.put("value", type.getMean());
            m.put("type", type.isHaveCode());
            list.add(m);
        }
        return list;
    }

}
