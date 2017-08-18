package com.basic.core.service;

import com.basic.core.entity.query.DicItemQuery;
import com.basic.core.entity.vo.DicItemVo;
import com.basic.core.entity.vo.GridVo;


public interface DicItemService {

    /*根据typeId分页查询*/
    GridVo<DicItemVo> getPageItems(DicItemQuery query) throws Exception;

    /*根据主键ID查询*/
    DicItemVo getSingle(Long itemId) throws Exception;

    /*保存字典项*/
    void saveDicItem(DicItemVo vo) throws Exception;

    /*更新字典项*/
    void updateDicItem(DicItemVo vo) throws Exception;

    /*删除字典项*/
    void removeDicItem(Long itemId) throws Exception;
}
