package com.yin.erp.user.info.dict.service;

import com.yin.erp.base.entity.vo.out.BackPageVo;
import com.yin.erp.base.exceptions.MessageException;
import com.yin.erp.user.info.dict.dao.DictDao;
import com.yin.erp.user.info.dict.dao.DictSizeDao;
import com.yin.erp.user.info.dict.entity.po.DictPo;
import com.yin.erp.user.info.dict.entity.po.DictSizePo;
import com.yin.erp.user.info.dict.entity.vo.DictVo;
import com.yin.erp.user.info.dict.entity.vo.in.DictDeleteVo;
import com.yin.erp.user.info.dict.enums.DictGoodsType;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 字典服务
 *
 * @author yin.weilong
 * @date 2018.11.11
 */
@Service
@Transactional(rollbackFor = Throwable.class)
public class DictService {

    @Autowired
    private DictDao dictDao;
    @Autowired
    private DictSizeDao dictSizeDao;

    /**
     * 保存
     *
     * @param vo
     * @throws Exception
     */
    public void save(DictVo vo) throws MessageException {
        DictPo po = new DictPo();
        if (StringUtils.isNotBlank(vo.getId())) {
            po = dictDao.findById(vo.getId()).get();
            //发送给队列，全局做数据更新 TODO
        }
        po.setType1(vo.getType1());
        po.setType2(vo.getType2());
        po.setCode(vo.getCode());
        po.setName(vo.getName());
        dictDao.save(po);
        if (DictGoodsType.SIZE_GROUP.name().equals(po.getType2())) {
            dictSizeDao.deleteByGroupId(po.getId());
            if (vo.getSizeList() != null) {
                Set setName = new HashSet<>();
                for (int i = 0; i < vo.getSizeList().size(); i++) {
                    DictSizePo dictSizePo = new DictSizePo();
                    dictSizePo.setOrderIndex(i);
                    dictSizePo.setGroupId(po.getId());
                    dictSizePo.setGroupName(po.getName());
                    dictSizePo.setName(vo.getSizeList().get(i));
                    dictSizeDao.save(dictSizePo);
                    setName.add(dictSizePo.getName().toUpperCase());
                }
                if (setName.size() != vo.getSizeList().size()) {
                    throw new MessageException("尺码存在重复");
                }
            }
        }

    }

    /**
     * 查询根据ID
     *
     * @param id
     * @return
     */
    public DictVo findById(String id) {
        DictPo dictPo = dictDao.findById(id).get();
        DictVo dictVo = new DictVo();
        dictVo.setId(dictPo.getId());
        dictVo.setCode(dictPo.getCode());
        dictVo.setName(dictPo.getName());
        dictVo.setType1(dictPo.getType1());
        dictVo.setType2(dictPo.getType2());
        if (DictGoodsType.SIZE_GROUP.name().equals(dictPo.getType2())) {
            dictVo.setSizeList(dictSizeDao.findNameByGroupId(dictPo.getId()));
        }
        return dictVo;
    }

    /**
     * 查询尺码组
     *
     * @param vo
     * @return
     */
    public BackPageVo<DictVo> findSizeGroupList(DictVo vo) {
        BackPageVo backPageVo = new BackPageVo();
        Page<DictPo> page = this.findDictPage(vo);
        backPageVo.setTotalElements(page.getTotalElements());
        List<DictVo> list = new ArrayList<>();
        for (DictPo dictPo : page.getContent()) {
            DictVo dictVo = new DictVo();
            dictVo.setId(dictPo.getId());
            dictVo.setName(dictPo.getName());
            dictVo.setType1(dictPo.getType1());
            dictVo.setType2(dictPo.getType2());
            dictVo.setSizeList(dictSizeDao.findNameByGroupId(dictPo.getId()));
            list.add(dictVo);
        }
        backPageVo.setContent(list);
        return backPageVo;
    }

    /**
     * 查询字典
     *
     * @param vo
     * @return
     */
    public Page<DictPo> findDictPage(DictVo vo) {
        Page<DictPo> page = dictDao.findAll((root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(criteriaBuilder.equal(root.get("type1"), vo.getType1()));
            predicates.add(criteriaBuilder.equal(root.get("type2"), vo.getType2()));
            if (StringUtils.isNoneBlank(vo.getCode())) {
                predicates.add(criteriaBuilder.like(root.get("code"), "%" + vo.getCode() + "%"));
            }
            if (StringUtils.isNoneBlank(vo.getName())) {
                predicates.add(criteriaBuilder.like(root.get("name"), "%" + vo.getName() + "%"));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        }, PageRequest.of(vo.getPageIndex() - 1, vo.getPageSize(), Sort.Direction.DESC, "createDate"));
        return page;
    }

    /**
     * 删除
     *
     * @param vo
     */
    public void delete(DictDeleteVo vo) {
        for (String id : vo.getIds()) {
            //查询货品/渠道引用情况 TODO
            if (DictGoodsType.SIZE_GROUP.name().equals(vo.getType2())) {
                dictSizeDao.deleteByGroupId(id);
            }
            dictDao.deleteById(id);
        }
    }


}
