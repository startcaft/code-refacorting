package com.basic.core.dao.master;

import com.basic.core.entity.DicItem;

import java.util.List;
import java.util.Map;

public interface DicItemMapper {

    int deleteByPrimaryKey(Long id);

    int insert(DicItem record);

    DicItem selectByPrimaryKey(Long id);

    List<DicItem> selectListDynamic(Map<String, Object> map);

    int updateByPrimaryKeySelective(DicItem record);
}