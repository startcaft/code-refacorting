package com.basic.core.dao.master;

import com.basic.core.entity.DicType;

import java.util.List;
import java.util.Map;

public interface DicTypeDao {

    int deleteByPrimaryKey(Long id);

    int insert(DicType record);

    DicType selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DicType record);

    List<DicType> selectListDynamic(Map<String,Object> param);
}